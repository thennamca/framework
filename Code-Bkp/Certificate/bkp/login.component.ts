import {Component, OnInit, Renderer} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { HttpClient } from '../shared/http-client/http-client';
import { GlobalService } from '../shared/global.service';
import { TranslateService } from 'ng2-translate';
declare var $:any;
@Component({

    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    providers: [HttpClient, GlobalService]

})
export class LoginComponent implements OnInit {

    public submitted: boolean = false;
    public errorEmail: string = '';
    public request: any = [];
    public getEmailId: string = '';
    public changePasswordDetails: any = { password: '', confirmPassword: '' };
    private flashMessageConfig: any = {};
    model: any = {};
    loading = false;
    returnUrl: string;
    public serviceUrl: any = {
        saveLogin: { url: 'oauth/token', mockurl: 'login/token.json', isMock: false },
        saveForgotPassword: { url: 'users/forgetpassword', mockurl: '', isMock: false }
    }
    constructor(private httpClient: HttpClient, private globalService: GlobalService, private renderer: Renderer,
    private route: ActivatedRoute, private router: Router, private translate: TranslateService) {
    }

    ngOnInit() {
        // let initalLoadElement = this.globalService.getInitialLoad();
        // this.renderer.setElementStyle(initalLoadElement, 'display', 'none');
        if (window.location.pathname == '/innometer/forgot-password') {
            this.globalService.isForgotValue = "forgot";
        } else if (window.location.pathname == '/innometer/reset-password') {
            this.globalService.isForgotValue = "reset";
        }
        else {
            this.globalService.isForgotValue = "login";
        }
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || "/dashboard";
    }
    
    setRefreshToken() {
        let refreshToken = window.localStorage.getItem('refreshToken');
        let value: any = window.localStorage.getItem('expiresTime');
        let callRefreshFunction: number = value - 5;
        let callOneTime: boolean = true;
       
       // function runtimer() {
            if (refreshToken && value && value > 0) {
                setInterval(function() {
                    //value--;
                    //console.log('callOneTime', value);
                    //if (value < callRefreshFunction && value > 0 && callOneTime == true) {
                        let param: any = { grant_type: 'refresh_token', refresh_token: refreshToken, client_id: 'innometer' }
                        callOneTime = false;
                        this.httpClient
                            .postAccessToken(this.serviceUrl.saveLogin.url, param)
                            .then(changeToken => {
                                if (changeToken && changeToken['_response_status'] == 'success') {
                                    window.localStorage.setItem('accessToken', this.getToken(changeToken['access_token']));
                                    window.localStorage.setItem('viewMenu', 'yes');
                                    if (changeToken['SYSYTEM_ROLE'] && changeToken['SYSYTEM_ROLE'] != null) {
                                        window.localStorage.setItem('role', this.getToken(changeToken['SYSYTEM_ROLE']));
                                    }
                                    this.timer();
                                } else {
                                    let errorMessage: string = '';
                                    this.translate.get('innometer.error_message.login').subscribe((message: string) => {
                                        errorMessage = message;
                                    });
                                    (changeToken['error']) ? this.setErrorFlashMessage(errorMessage) : '';
                                }
                            });
                    //}

                }, callRefreshFunction);
            }
       // }
       // runtimer();
    }

    saveLogin(formValid: boolean) {
        this.submitted = true;
        let params: string = '?grant_type=password' + '&username=' + encodeURIComponent(this.model.userName) + '&password=' + this.model.password + '&client_id=innometer';
        if (formValid) {
            this.httpClient
                .postAccessToken(this.serviceUrl.saveLogin.url + params, params)
                .then(saveLogin => {
                    if (saveLogin && saveLogin['_response_status'] == 'success') {
                        window.localStorage.setItem('accessToken', this.getToken(saveLogin['access_token']));
                        window.localStorage.setItem('refreshToken', this.getToken(saveLogin['refresh_token']));
                        window.localStorage.setItem('expiresTime', saveLogin['expires_in']);
                        window.localStorage.setItem('viewMenu','yes');
                        if (saveLogin['SYSYTEM_ROLE'] && saveLogin['SYSYTEM_ROLE'] != null) {
                            window.localStorage.setItem('role', this.getToken(saveLogin['SYSYTEM_ROLE']));
                        }
                        this.getUserProfile();
                       // this.setRefreshToken();
                    } else {
                            let errorMessage: string = saveLogin.error_description;
                        console.log('errorMessage',errorMessage);
                      /*this.translate.get('innometer.error_message.login').subscribe((message: string) => {
                                errorMessage = message;
                            });*/
                        (saveLogin['error']) ? this.setErrorFlashMessage(errorMessage) : '';
                    }
                });
        }
    }

    getUserProfile(): void {
        $('login').hide();
        this.httpClient
            //.get('common/menu.json', true)
            .get('common/authorities', false)
            .then(innoMeterHeaders => {
                if (innoMeterHeaders['_response_status'] === 'success') {
                    //console.log('innoMeterHeaders',innoMeterHeaders);
                    window.localStorage.setItem('userProfile', JSON.stringify(innoMeterHeaders));
                    window.localStorage.setItem('clientImage',innoMeterHeaders['clientImage'])
                    let image = window.localStorage.getItem('clientImage');
                    let role = window.localStorage.getItem('role');
                    let redirectURL = '/dashboard';
                    //console.log('role'+role);
					if(role == 'SUPERADMIN' ){
                        localStorage.removeItem('viewMenu');
                        window.localStorage.setItem('viewMenu','no');
                        redirectURL = '/settings/clients/client-list'
                    }
                    this.router.navigate([redirectURL]);
                    /*if (window.location.pathname == '/innometer' || window.location.pathname == '/innometer/' || window.location.pathname == '/innometer/login' || window.location.pathname == '/') {
                        this.router.navigate([redirectURL]);
                    }*/
                } else {
                    localStorage.removeItem('userProfile');
                    if (innoMeterHeaders['_response_status'] === 'error') {
                        if (innoMeterHeaders['message'][0]['message'] === 'Invalid User') {
                            this.logout();
                        } else {
                            this.router.navigate(['/permissionDenied']);
                        }
                    } else {
                        this.router.navigate(['/permissionDenied']);
                    }
                }
            });
    }

    logout() {
        let idToken = localStorage.getItem('id_token');
        localStorage.removeItem('access_token');
        localStorage.removeItem('id_token');
        localStorage.removeItem('userProfile');
        localStorage.removeItem('viewMenu');
        localStorage.removeItem('refreshToken');
        localStorage.removeItem('expiresTime');
        localStorage.clear();
    }
    goToLogin() {
        let contextOrigin: any = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : ''); // Hostname
        let contextURL: string = 'innometer';
        window.location.href = contextOrigin + '/' + contextURL;
    }

    getToken(token) {
        let sess_token = token.replace('"', "");
        let regex = new RegExp('"', 'g');
        return sess_token.replace(regex, '');
    }
    setErrorFlashMessage(errorMsg: any) {
        this.globalService.setFlashMessage(errorMsg, 'danger', false);
        this.flashMessageConfig = this.globalService.getFlashMessage();
        this.globalService.clearFlashMessage();
    }

    isForgot(value: string) {
        if (value == 'forgot') {
            this.globalService.isForgotValue = 'forgot';
        } else if (value == 'reset') {
            this.globalService.isForgotValue = 'reset';
        }
        else {
            this.globalService.isForgotValue = 'login';
        }
    }
    setPassword(passwordValue: string) {
        let meter = document.getElementById("meter");
        let no = 0;
        if (passwordValue != "") {
            // If the password length is less than or equal to 6
            if (passwordValue.length <= 8) no = 1;

            // If the password length is greater than 6 and contain alphabet,number,special character respectively
            if (passwordValue.length > 8 && ((passwordValue.match(/[a-z]/) && passwordValue.match(/[0-9]/) && (passwordValue.match(/[A-Z]/) || passwordValue.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/))))) no = 2;

            // If the password length is greater than 6 and must contain alphabets,numbers and special characters
            if (passwordValue.length > 8 && passwordValue.match(/[a-z]/) && passwordValue.match(/[0-9]/) && passwordValue.match(/[A-Z]/) && passwordValue.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/)) no = 3;
        }
        else {
        }
    }
    globalValidEmailAddress(email) {
        this.globalService.validEmailAddress(email);
    }
    globalResetvalidEmailAddress() {
        this.globalService['errorEmail'] = "success";
    }
    saveForgotPassword(formValid: boolean) {
        this.submitted = true;
        let param = '?emailId=' + encodeURIComponent(this.getEmailId)
        if (formValid) {
            this.httpClient
                .postWithoutToken(this.serviceUrl.saveForgotPassword.url + param, false)
                .then(savePassword => {
                    if (savePassword && savePassword['_response_status'] == 'success') {
                        this.globalService.setFlashMessage('Password Reset link sent successfully to the registered emailId', 'success', true);
                        this.flashMessageConfig = this.globalService.getFlashMessage();
                        this.globalService.clearFlashMessage();
                        this.getEmailId = '';
                        this.submitted = false;
                        // commanded for 21 issue
                        //this.globalService.isForgotValue = 'login';
//                        setTimeout(function() {
//                            localStorage.clear();
//                            let contextOrigin: any = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : ''); // Hostname
//                            let contextURL: string = 'innometer/login';
//                            window.location.href = contextOrigin + '/' + contextURL;
//                        }, 2000);
                    } else {
                        (savePassword['message']) ? this.setErrorFlashMessage(savePassword['message']) : '';
                    }
                });
        }
    }
}