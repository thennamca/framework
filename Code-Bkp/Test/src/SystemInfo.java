import java.net.InetAddress;
import java.net.NetworkInterface;

public class SystemInfo {
	
	/**
     * Method for get System Name
     * @return  Host name
     */
	public String getSystemName(){  
        try{
            InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
            String name = inetaddress.getHostName();   //Get Host Name
            System.out.println("SystemName:::"+name);
            return name;   //return Host Name
        }
        catch(Exception E){
            E.printStackTrace();  //print Exception StackTrace
            return null;
        }
    }
    
    /**
     * method to get Host IP
     * @return Host IP Address
     */
	public String getIPAddress(){
         try{
            InetAddress inetaddress=InetAddress.getLocalHost();  //Get LocalHost refrence
            String ip = inetaddress.getHostAddress();  // Get Host IP Address
            System.out.println("system IP:::"+ip);
            return ip;   // return IP Address
        }
        catch(Exception E){
            E.printStackTrace();  //print Exception StackTrace
            return null;
        }
         
    }
    
    /**
     * method to get Host Mac Address
     * @return  Mac Address
     */
	public String getWindowsMAC(){
         try{
            InetAddress inetaddress=InetAddress.getLocalHost(); //Get LocalHost refrence
             
            //get Network interface Refrence by InetAddress Refrence
            NetworkInterface network = NetworkInterface.getByInetAddress(inetaddress); 
            byte[] macArray = network.getHardwareAddress();  //get Harware address Array
            StringBuilder str = new StringBuilder();
             
            // Convert Array to String 
            for (int i = 0; i < macArray.length; i++) {
                    str.append(String.format("%02X%s", macArray[i], (i < macArray.length - 1) ? "-" : ""));
            }
            String macAddress=str.toString();
         
            return macAddress; //return MAc Address
        }
        catch(Exception E){
            E.printStackTrace();  //print Exception StackTrace
            return null;
        } 
    }
	
	public String getMAC() {
		SystemInfo systemInfo = new SystemInfo();
		String mac = null;
		try {
			String OSName = System.getProperty("os.name");
			if (OSName.contains("Windows")) {
				mac = systemInfo.getWindowsMAC();
			} else {
				mac = systemInfo.getMAC4Linux("eth0");
				if (mac == null) {
					mac = systemInfo.getMAC4Linux("eth1");
					if (mac == null) {
						mac = systemInfo.getMAC4Linux("eth2");
						if (mac == null) {
							mac = systemInfo.getMAC4Linux("usb0");
						}
					}
				}
			}
		} catch (Exception E) {
			System.err.println("System Mac Exp : " + E.getMessage());
		}
		System.out.println("MacAddress:::"+mac);
		return mac;
	}
	
	/**
     * Method for get MAc of Linux Machine
     * @param name
     * @return 
     */
    public String getMAC4Linux(String name){
        try {
            NetworkInterface network = NetworkInterface.getByName(name);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            return (sb.toString());
        }
        catch (Exception E) {
            System.err.println("System Linux MAC Exp : "+E.getMessage());
            return null;
        } 
    } 
    
    public static void main(String[] args) {
    	SystemInfo systemInfo = new SystemInfo();
    	systemInfo.getSystemName();
    	systemInfo.getMAC();
    	systemInfo.getIPAddress();
	}

}
