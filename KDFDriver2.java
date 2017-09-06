//Keyword Driven Testing( Keywords like- clickLink, clickElement, verifyText, openBrowser, closeBrowser, navigateBrowser, typeEdit) 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel. XSSFCell;
import org.apache.poi.xssf.usermodel. XSSFRow;
import org.apache.poi.xssf.usermodel. XSSFSheet;
import org.apache.poi.xssf.usermodel. XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class KDFDriver2 {
	WebDriver myD;
	String[][] xTC, xTS;
	int rowsTC, rowsTS;
	String xlPath;
	String tcID, tcDesc, tcExecute, tcResult;
	String tsID, tsNum, tsDesc, tsKW, tsEBy, tsEID, tsTD, tsResult;
	
	@Before
	public void getStarted() throws Exception{
		xlPath = "C:\\Users\\Jayashri\\eclipse-workspace\\KDF\\Test Data\\Keyword Driven Testing data sheet2.xlsx";
		xTC = readXL(xlPath, "Test Cases");
		xTS = readXL(xlPath, "Test Steps");
		
		// Get the row counts
		rowsTC = xTC[0].length;
		rowsTS = xTS[0].length;
		
		System.out.println("TC Rows are : " + rowsTC);
		System.out.println("TS Rows are : " + rowsTS);
		
	}
	
	@Test
	public void myTest() {
		for (int i = 1; i < rowsTC; i++) {
			// Go through each row in TC.
			tcID = xTC[i][0];
			tcDesc = xTC[i][1];
			tcExecute = xTC[i][2];
			tcResult = xTC[i][3];
			
			// Check if the execution is Y or N
			if (tcExecute.equals("Y")) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Now EXECUTING: " + tcID);
				
				// If Y, then go to each row in TS sheet
				for (int j = 1; j < rowsTS; j++) {
					tsID = xTS[j][0];
					tsNum = xTS[j][1];
					tsDesc = xTS[j][2];
					tsKW = xTS[j][3];
					tsEBy = xTS[j][4];
					tsEID = xTS[j][5];
					tsTD = xTS[j][6];
					tsResult = xTS[j][7];
					
					// See if the TCID's match
					if (tcID.equals(tsID)) {
						// Get the corresponding TS's KW, EID, TD
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("KW : " + tsKW);
						System.out.println("EID By : " + tsEBy);
						System.out.println("EID : " + tsEID);
						System.out.println("TD : " + tsTD);
						// Call the function that can execute the KW, pass the variables
						if (tsKW.equals("openBrowser")) {
							openBrowser(tsTD);
						}
						if (tsKW.equals("closeBrowser")) {
							closeBrowser();
						}
						
						
					} else {
						
					}

					// Get the Test Results back into the excel

					
					
				}
					
			}
			
		}
	}
	
	@After
	public void getCleaned() {
		
	}

	
	// Reusable Keyword Functions
	
	public void clickLink(String fEID) {
		//Purpose : Will click on a link using the link text
		//I/P:		LinkText
		//O/P:		N/A
		//Created by/on: Karthik Kosireddi, 23rd Aug 2017
		//Modified by/on:Alex, 23rd Aug
		
		myD.findElement(By.linkText(fEID)).click();
	}
	
	public void clickElement(String fEID) {
		myD.findElement(By.xpath(fEID)).click();
	}
	
	public String verifyText(String fEID, String fTD) {
		String fText = myD.findElement(By.xpath(fEID)).getText();
		System.out.println("Exp Text : " + fTD);
		System.out.println("Act Text : " + fText);
		if (fText.equals(fTD)) {
			return "Pass";
		} else {
			return "Fail";
		}
	}
	public void openBrowser(String fTD) {
		
		if (fTD.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\geckodriver.exe");
			myD = new FirefoxDriver();			
		}
		if (fTD.equals("IE")) {
			System.setProperty("webdriver.Chrome.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\chromedriver.exe");
			myD = new InternetExplorerDriver();			
		}
		if (fTD.equals("Chrome")) {
			System.setProperty("webdriver.internetexplorer.driver", "C:\\Users\\Jayashri\\workspace\\CSDT Drivers\\internetexplorerdriver.exe");
			myD = new ChromeDriver();			
		}
		//myD.manage().window().maximize();
	}

	public void closeBrowser() {
		myD.quit();
	}
	public void navigateBrowser(String fTD) {
		myD.get(fTD);
	}
	public void typeText(String fEID, String fTD) {
		myD.findElement(By.xpath(fEID)).clear();
		myD.findElement(By.xpath(fEID)).sendKeys(fTD);
	}
	
	// Method to read XL
			public static String[][] readXL(String fPath, String fSheet) throws Exception{
				// Inputs : XL Path and XL Sheet name
				// Output : 2D Array
				String[][] xData;
				int xRows, xCols;

				File myxl = new File(fPath);                                
				FileInputStream myStream = new FileInputStream(myxl);                                
				XSSFWorkbook myWB = new XSSFWorkbook(myStream);                                
				XSSFSheet mySheet = myWB.getSheet(fSheet);                                 
				xRows = mySheet.getLastRowNum()+1;                                
				xCols = mySheet.getRow(0).getLastCellNum();   
				xData = new String[xRows][xCols];    
				
				for (int i = 0; i < xRows; i++) {                           
					XSSFRow row = mySheet.getRow(i);
					for (int j = 0; j < xCols; j++) {                               
						XSSFCell cell = row.getCell(j);
						String value = "-";
						if (cell!=null){
							value = cellToString(cell);
						}
						xData[i][j] = value; 
						System.out.print(" >> ");
						System.out.print(value);
					}        
					System.out.println("");
				}    
				myxl = null; // Memory gets released
				return xData;
			}

			//Change cell type
			public static String cellToString(XSSFCell cell) { 
				// This function will convert an object of type excel cell to a string value
				int type = cell.getCellType();                        
				Object result;                        
				switch (type) {                            
				case XSSFCell.CELL_TYPE_NUMERIC: //0                                
					result = cell.getNumericCellValue();                                
					break;                            
				case XSSFCell.CELL_TYPE_STRING: //1                                
					result = cell.getStringCellValue();                                
					break;                            
				case XSSFCell.CELL_TYPE_FORMULA: //2                                
					throw new RuntimeException("We can't evaluate formulas in Java");  
				case XSSFCell.CELL_TYPE_BLANK: //3                                
					result = "%";                                
					break;                            
				case XSSFCell.CELL_TYPE_BOOLEAN: //4     
					result = cell.getBooleanCellValue();       
					break;                            
				case XSSFCell.CELL_TYPE_ERROR: //5       
					throw new RuntimeException ("This cell has an error");    
				default:                  
					throw new RuntimeException("We don't support this cell type: " + type); 
				}                        
				return result.toString();      
			}

			// Method to write into an XL
			public static void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{

				File outFile = new File(fPath);
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet osheet = wb.createSheet(fSheet);
				int xR_TS = xData.length;
				int xC_TS = xData[0].length;
				for (int myrow = 0; myrow < xR_TS; myrow++) {
					XSSFRow row = osheet.createRow(myrow);
					for (int mycol = 0; mycol < xC_TS; mycol++) {
						XSSFCell cell = row.createCell(mycol);
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(xData[myrow][mycol]);
					}
					FileOutputStream fOut = new FileOutputStream(outFile);
					wb.write(fOut);
					fOut.flush();
					fOut.close();
				}
			}

}