
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BasicLogin {

	public static void main(String[] args) throws Exception {
		login();

	}
	
    public static void skyNetLogger(String fileName, String logMessage) throws Exception {
    	
    	LocalDateTime now = LocalDateTime.now();
    	String logTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    	File logFile = new File(fileName);
    	logFile.createNewFile();
    	PrintWriter pw = new PrintWriter(new FileOutputStream(logFile, true));
    	pw.println(logTime + ": " + logMessage);
    	pw.close();
    	
    }
    
    public static Boolean loginFile(String user, String password) throws Exception{
    	
    	String providedUser = "";
    	String providedPass = "";
    	
    	
    	System.out.println("provide a path to a file(relative path will do) "
    			         + "containing username in the first line "
    			         + "and the password in the second line."); 
    	Scanner in = new Scanner(System.in);
    	String fileName = in.nextLine();
    	
    	File loginFile = new File(fileName);
    	
    	BufferedReader br = Files.newBufferedReader(loginFile.toPath()); 
    	int lineno  = 0;
    	String line = null;
    	while ( (line = br.readLine()) != null) {
    		if (lineno == 0) {
    		   providedUser = line;
    		}
    		if (lineno == 1) {
	    		   providedPass = line;
	    	}
    		if (lineno == 3) {
    			line = null;
    		}
    		lineno ++;
    	}
        
    	if (!(user.equals(providedUser))) {
        	String logEntry = "Failed login attempt, invalid username: " + providedUser;
        	skyNetLogger("skynet.log", logEntry);
    	}
    	
    	if (!(password.equals(providedPass))) {
    		String logEntry = "FAILED LOGIN - wrong password - of user \'" + providedUser + "\'";
            skyNetLogger("skynet.log", logEntry);
    	}
    	
    	if (user.equals(providedUser) && password.equals(providedPass)) {
            String logEntry = "Successful login of user \'" + providedUser + "\'";
            skyNetLogger("skynet.log", logEntry);
    		return true;
    	}
    	
    	return false;
    	
    }
    public static Boolean loginConsole (String user, String pass) throws Exception {

	    	System.out.println("provide Username");
	    	Console con = System.console();
            String providedUser = con.readLine();
            if (!(providedUser.equals(user))) {
            	System.out.println("username does not exist");
            	String logEntry = "Failed login attempt, invalid username: " + providedUser;
            	skyNetLogger("skynet.log", logEntry);
            	return false;
            	
            }
        	System.out.println("provide password for username: \'" + providedUser + "\' and hit enter");
        	char[] providedPass = con.readPassword();
        	String verifyPass = new String(providedPass); // nie rozumiem dlaczego providedPass.toString nie dziala :(
        	if (!(verifyPass.equals(pass))) {
        	  String logEntry = "FAILED LOGIN - wrong password - of user \'" + providedUser + "\'";
        	  skyNetLogger("skynet.log", logEntry);
        	  return false; 
        	}
        	
        	String logEntry = "Successful login of user \'" + providedUser + "\'";
            skyNetLogger("skynet.log", logEntry);
        	return true;
    }
    
    public static void insideSkyNet(Boolean isValid) {
    	
    	if (isValid) {
    		System.out.println("Welcome to SkyNet...\n\n\n");
    		
    	} else {
    		System.out.println("You have no business here!\n You shall be exterminated\n\n\n");
    	}
    	
    }
    
    public static int getInt() {
    	
    	Scanner in = new Scanner(System.in);
    	Boolean isInt;
    	String welcomeMessage =  "Welcome to Skynet. \nSelect a login method\n"
          		                  + "1) - login via console\n"
                                  + "2) - use a login file\n";
    	
    	do {
    		System.out.println(welcomeMessage);
    		if (in.hasNextInt()) {
    			isInt = true;
    		} else {
    			isInt = false;
    			String word = in.nextLine();
    			System.out.println("Text is not understood");
    		}
    	}
        while (!isInt);
    	
    	int choice = in.nextInt();
    	return choice;
    }
    
    
    public static void login() throws Exception{
    	String user = "jacek";
    	String pass = "placek";
    	Boolean activeSession = false;
        
        while (activeSession == false) {
        	int input = getInt();
            switch (input) {
                case 1:
	    	      activeSession = loginConsole(user, pass);
                  insideSkyNet(activeSession);
	    	      break;
                case 2:
  	    	      activeSession = loginFile(user, pass);
  	    	      insideSkyNet(activeSession);
                  break;
	       }
       }
    }

}
