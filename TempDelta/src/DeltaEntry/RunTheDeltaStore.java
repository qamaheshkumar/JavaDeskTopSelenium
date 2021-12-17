package DeltaEntry;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class RunTheDeltaStore {

    // Text files folder path
	
	public static String TextFileFolderPath = "";
	// DeltaStore Medcare exe file path
	public static String appPath = "";
	public static int intTextBoxNumber = 0;
	public static String[] inputIds = 
		{ "textbox_srno",
			"textBox_name",
			"textbox_id",
			"textBox_address",
			"textBox_lictype",
			"textBox_licno",
			"textBox_manuname",
			"textBox_manufacturer_id",
			"textBox_manuaddr",
			"textBox_pres1",
			"textBox_pres2",
			"textBox_pres3",
			"textBox_pres4",
			"textBox_pres5",
			"textBox_amountpaid"
		};

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        WiniumDriver driver = null;
        DesktopOptions option = new DesktopOptions();
        option.setApplicationPath(RunNewDeltaStore.appPath);
        System.out.println(RunNewDeltaStore.appPath);
        option.setDebugConnectToRunningApp(false);
        option.setLaunchDelay(2);
        driver = new WiniumDriver(new URL("http://localhost:9999"), option);
        String parent_window = driver.getWindowHandle();
        Thread.sleep(500);
        if(driver.findElementById("loginbutton").isDisplayed()) {
        	driver.switchTo().window(parent_window);
        	driver.findElementById("username").sendKeys("");
        	driver.findElementById("password").sendKeys("");
        	driver.findElementById("loginbutton").click();
        	Thread.sleep(1000);
        	if(driver.findElementById("textbox_srno").isDisplayed()) {
        		System.out.println("present");
        	}
//        	System.exit(0);
        	File folder = new File(RunNewDeltaStore.TextFileFolderPath);
        	File[] listOfFiles = folder.listFiles();
        	for (File eachFile : listOfFiles) {
        		String EachTxtFilePath = "";
        		String fileContent = "";
        		if (eachFile.getName().toLowerCase().endsWith((".txt"))) {
        			EachTxtFilePath = RunNewDeltaStore.TextFileFolderPath.concat(eachFile.getName().trim());
        			System.out.println(EachTxtFilePath);
        	        
        	        //driver.switchTo().window(parent_window);    		
                	try {
    					fileContent = FileUtils.readFileToString(new File(EachTxtFilePath), StandardCharsets.UTF_8);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                 	if(!fileContent.trim().isEmpty()) {
                    	String lines[] =  fileContent.toString().split("\\n");
//                		Scanner in = new Scanner(System.in);
//                		Thread.sleep(1000);
//                		String eachLineSting = "";
//                    	for (String eachline : lines) {
//                    		if(in.hasNextInt()) {
//                            	intTextBoxNumber = in.nextInt();
//                            	System.out.println("intTextBoxNumber  "+intTextBoxNumber);
//                    		}
//                        	if(intTextBoxNumber > 0) {
//                        		driver.switchTo().window(parent_window);
//                        		WebElement elmTextBox = driver.findElementById(RunNewDeltaStore.inputIds[intTextBoxNumber-1]);
//                        		elmTextBox.clear();
//                        		eachLineSting = lines[intTextBoxNumber-1].replaceAll("[^a-zA-Z0-9 -(),]+","");
//                        		elmTextBox.sendKeys(eachLineSting);
//                        	}
//    					}
//                    	in.reset();                       	
//                    	driver.switchTo().window(parent_window);
                    	Thread.sleep(1000);
                    	driver.findElementById("textbox_srno").sendKeys(lines[0].replaceAll("[^a-zA-Z0-9 -(),]+",""));
                    	driver.findElementById("textBox_name").sendKeys((lines[1].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textbox_id").sendKeys(lines[2].replaceAll("[^a-zA-Z0-9 -(),]+",""));
                    	driver.findElementById("textBox_address").sendKeys(lines[3].replaceAll("[^a-zA-Z0-9 -(),]+",""));
                    	driver.findElementById("textBox_lictype").sendKeys((lines[4].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_licno").sendKeys(lines[5].replaceAll("[^a-zA-Z0-9 -(),]+",""));
                    	driver.findElementById("textBox_manuname").sendKeys(lines[6].replaceAll("[^a-zA-Z0-9 -(),]+",""));
                    	driver.findElementById("textBox_manufacturer_id").sendKeys((lines[7].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("O", "0"));
                    	driver.findElementById("textBox_manuaddr").sendKeys(lines[8].replaceAll("[^a-zA-Z0-9 -()]+",""));
                    	driver.findElementById("textBox_pres1").sendKeys((lines[9].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_pres2").sendKeys((lines[10].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_pres3").sendKeys((lines[11].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_pres4").sendKeys((lines[12].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_pres5").sendKeys((lines[13].replaceAll("[^a-zA-Z0-9 -(),]+","")).replace("1", "i"));
                    	driver.findElementById("textBox_amountpaid").sendKeys(lines[14]);
                    	WebDriverWait wait = new WebDriverWait(driver, 1000);
                        wait.until(ExpectedConditions.elementToBeClickable(By.name("OK")));
                        WebElement ChildWindow = driver.findElementByClassName("#32770");
                        Thread.sleep(1000);
                        ChildWindow.findElement(By.name("OK")).click();
                        Thread.sleep(500);
                	}
        		}
    		}        	
        }
    	driver.close();
    }

}
