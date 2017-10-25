package inkass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Marshruts {

	private FileInputStream Marshruts;
	private Workbook curientWB_open;
	private Sheet curientSheet;
		

		private void setCurientSheet(int index) {
			curientSheet = curientWB_open.getSheetAt(index);

		}

			private String getDir() {
				return System.getProperty("user.home")+"\\Downloads\\";//*********потом поменять на папку ресурсов
			}
		
				private void OpenFileBolvan() throws IOException{								
					Marshruts = new FileInputStream(this.getDir()+"МАРШРУТЫ_болван.xlsx");
					curientWB_open = new XSSFWorkbook(Marshruts);
								   	        
				}
		
		
					private void SaveFile() throws IOException{
						
						FileOutputStream out = new FileOutputStream(new File(this.getDir()+"МАРШРУТЫ.xlsx"));
						curientWB_open.write(out);
						out.close();
					}
	
	
	public void OpenFile(){
		try {
			this.OpenFileBolvan();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
					
					
	public void writeSheet(ArrayList <String[]> inkassDataTerminal, int indexSheet, boolean LO){
					      
		    this.setCurientSheet(indexSheet);
		    String lostSymbolInMarshIndex = "";

			    for (int i = 0; i < inkassDataTerminal.size();i++){
				      
						curientSheet.getRow(i+3).getCell(1).setCellValue(inkassDataTerminal.get(i)[0]);
						curientSheet.getRow(i+3).getCell(2).setCellValue(Integer.parseInt(inkassDataTerminal.get(i)[1]));
						curientSheet.getRow(i+3).getCell(3).setCellValue(inkassDataTerminal.get(i)[2]);
						curientSheet.getRow(i+3).getCell(5).setCellValue(inkassDataTerminal.get(i)[3]);
						
							if (inkassDataTerminal.get(i)[4].compareTo("СК") == 0){
								curientSheet.getRow(i+3).getCell(5).setCellStyle(this.lightCK());
							}
								if (LO == true){
									
									lostSymbolInMarshIndex = Character.toString(inkassDataTerminal.get(i)[5]
											  .charAt(inkassDataTerminal.get(i)[5].length()-1));
										
										switch (lostSymbolInMarshIndex){
										
											case "1":	curientSheet.getRow(i+3).getCell(1).setCellStyle(this.lightLoPart1());break;
											case "2":	curientSheet.getRow(i+3).getCell(1).setCellStyle(this.lightLoPart2());break;
									
										}
								}
							
			    }
		this.finisherSheet(indexSheet, inkassDataTerminal.size());			
	}
		
	
			public void saveAndClose(){		
				try {
					this.SaveFile();
				} catch (IOException e) {
					e.printStackTrace();
				}					
									
				try {
					Marshruts.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	
	
	private void copyRow(int resourceRow, Row targetRow, int firstCell, int endCell){
		
		for (int i = firstCell; i < endCell+1; i++){
			targetRow.createCell(i);
			curientSheet.getRow(targetRow.getRowNum()).getCell(i).setCellStyle(curientSheet.getRow(resourceRow).getCell(i).getCellStyle());
			
				try{
					curientSheet.getRow(targetRow.getRowNum()).getCell(i).setCellValue(curientSheet.getRow(resourceRow).getCell(i).getStringCellValue());
				} catch(Exception e){
					curientSheet.getRow(targetRow.getRowNum()).getCell(i).setCellFormula(curientSheet.getRow(resourceRow).getCell(i).getCellFormula());
				}
		}			
	}
	
	private void finisherSheet(int indexSheet, int finalRow){

				finalRow = finalRow +3;
				curientSheet.removeRow(curientSheet.getRow(finalRow));
				curientSheet.removeRow(curientSheet.getRow(finalRow+1));
				curientSheet.removeRow(curientSheet.getRow(finalRow+2));
				curientSheet.removeRow(curientSheet.getRow(finalRow+3));
				curientSheet.removeRow(curientSheet.getRow(finalRow+4));
				
				
				
				Row row14 = curientSheet.createRow(finalRow+1);
				Row row15 = curientSheet.createRow(finalRow+2);
				Row row17 = curientSheet.createRow(finalRow+4);
				
				this.copyRow(304, row14, 3, 3);
				this.copyRow(305, row15, 3, 3);
				this.copyRow(307, row17, 1, 1);
				this.copyRow(307, row17, 5, 5);	
				
				
				for (int i = finalRow+5 ; i<308; i++){
					
					curientSheet.removeRow(curientSheet.getRow(i));	
					
				}
					
				
				curientWB_open.setPrintArea(
						indexSheet, //sheet index
			    	      1, //start column
			    	      11, //end column
			    	      0, //start row
			    	      finalRow+4 //end row*********************************************************переменная числа строк в таблице
			    	      );
		
	}
	
	private CellStyle lightCK(){
	
			//************************************************************************для выделения СК голубым****			
			Font font1 = curientWB_open.createFont();
			font1.setFontName("Times New Roman");
			font1.setColor((short) 0);
			font1.setFontHeightInPoints((short) 10);
			
			CellStyle style1 = curientWB_open.createCellStyle();
		      style1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		      style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		      style1.setFont(font1);
		      style1.setWrapText(true);
		      style1.setAlignment(HorizontalAlignment.CENTER);
		      style1.setVerticalAlignment(VerticalAlignment.CENTER);
		      style1.setBorderBottom(CellStyle.BORDER_THIN);
		      style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		      style1.setBorderLeft(CellStyle.BORDER_THIN);
		      style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		      style1.setBorderRight(CellStyle.BORDER_THIN);
		      style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		      style1.setBorderTop(CellStyle.BORDER_THIN);
		      style1.setTopBorderColor(IndexedColors.BLACK.getIndex());
		//***************************************************************************************************
		      
		    return style1;  
	}
	
	private CellStyle lightLoPart1(){
		
		//************************************************************************для выделения СК голубым****			
		Font font2 = curientWB_open.createFont();
		font2.setFontName("Times New Roman");
		font2.setColor((short) 0);
		font2.setFontHeightInPoints((short) 10);
		
		CellStyle style2 = curientWB_open.createCellStyle();
	      style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	      style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	      style2.setFont(font2);
	      style2.setWrapText(true);
	      style2.setAlignment(HorizontalAlignment.LEFT);
	      style2.setVerticalAlignment(VerticalAlignment.CENTER);
	      style2.setBorderBottom(CellStyle.BORDER_THIN);
	      style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	      style2.setBorderLeft(CellStyle.BORDER_THIN);
	      style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	      style2.setBorderRight(CellStyle.BORDER_THIN);
	      style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
	      style2.setBorderTop(CellStyle.BORDER_THIN);
	      style2.setTopBorderColor(IndexedColors.BLACK.getIndex());
	//***************************************************************************************************
	      
	    return style2;  
}
		private CellStyle lightLoPart2(){
				
				//************************************************************************для выделения СК голубым****			
				Font font3 = curientWB_open.createFont();
				font3.setFontName("Times New Roman");
				font3.setColor((short) 0);
				font3.setFontHeightInPoints((short) 10);
				
				CellStyle style3 = curientWB_open.createCellStyle();
			      style3.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			      style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			      style3.setFont(font3);
			      style3.setWrapText(true);
			      style3.setAlignment(HorizontalAlignment.LEFT);
			      style3.setVerticalAlignment(VerticalAlignment.CENTER);
			      style3.setBorderBottom(CellStyle.BORDER_THIN);
			      style3.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			      style3.setBorderLeft(CellStyle.BORDER_THIN);
			      style3.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			      style3.setBorderRight(CellStyle.BORDER_THIN);
			      style3.setRightBorderColor(IndexedColors.BLACK.getIndex());
			      style3.setBorderTop(CellStyle.BORDER_THIN);
			      style3.setTopBorderColor(IndexedColors.BLACK.getIndex());
			//***************************************************************************************************
			      
			    return style3;  
		}
	
	
	
}