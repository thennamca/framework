package com.techmango.framework.springelasticsearch.common.exception;



import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private MessageSourceAccessor accessor;

	public GlobalExceptionHandler(MessageSource messageSource) {
		accessor = new MessageSourceAccessor(messageSource);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorVM processException(BusinessException exc) {
		return new ErrorVM(exc.getMessage());
	}
	
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorVM processException(ApplicationException exc) {
		return new ErrorVM(exc.getMessage());
	}
	
	@ExceptionHandler(AuthoritiesException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorVM processException(AuthoritiesException exc) {
		return new ErrorVM(exc.getMessage());
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ErrorVM processException(HttpRequestMethodNotSupportedException exc) {
		return new ErrorVM(exc.getMessage());
	}
	
	/*@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorVM processAccessDeniedxception(AccessDeniedException exc) {
		return new ErrorVM(exc.getMessage());
	}*/
	
	@ExceptionHandler(ConcurrencyFailureException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorVM processConcurencyError(ConcurrencyFailureException ex) {
		return new ErrorVM("Concurrency failure");
	}
	
	/*private ErrorVM populateErrorVM(Exception ex) {
		logger.error("Exception : {}", ex);

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(ApplicationConstants.MESSAGE_SOURCE);
		// messageSource.setCacheSeconds(10); // reload messages every 10 seconds

		if (StringUtils.isNotBlank(InnoMeterOAuthTokenDetails.getUserLanguage())) {
			accessor = new MessageSourceAccessor(messageSource,new Locale(InnoMeterOAuthTokenDetails.getUserLanguage()));
		}  
		String message = accessor.getMessage(ex.getLocalizedMessage());

		ErrorVM errorResponse = new ErrorVM(message);
		return errorResponse;
	}*/

}