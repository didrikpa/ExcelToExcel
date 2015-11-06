package controllers;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class WriterController {

    public void writeFile(ArrayList<ArrayList<String>> bAndSNumbers,ArrayList<ArrayList<String>> batchAndSerial, String fileOne ){
        try{
            FileInputStream fs = new FileInputStream(fileOne);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet mySheet = workbook.getSheetAt(0);
            System.out.println(batchAndSerial);
            int batch = 0;
            int serial = 0;
            if (batchAndSerial.get(0).get(0).matches("Batch")){
                if (batchAndSerial.get(1).get(0).matches("Serial")){
                    batch = Integer.parseInt(batchAndSerial.get(0).get(1));
                    serial = Integer.parseInt(batchAndSerial.get(1).get(1));
                }
            }
            else if(batchAndSerial.get(0).get(0).matches("Serial")){
                if (batchAndSerial.get(1).get(0).matches("Batch")) {
                    serial = Integer.parseInt(batchAndSerial.get(0).get(1));
                    batch = Integer.parseInt(batchAndSerial.get(1).get(1));
                }
            }


            for (int i = 0; i <bAndSNumbers.size() ; i++) {
                Row r = mySheet.getRow(Integer.parseInt(bAndSNumbers.get(i).get(2)));
                if (r == null){
                    r = mySheet.createRow(Integer.parseInt(bAndSNumbers.get(i).get(2)));
                }
                Cell c = r.getCell(0);
                if (bAndSNumbers.get(i).get(1) == "Batch"){
                    c = r.getCell(batch);
                }
                else if (bAndSNumbers.get(i).get(1) == "Serial"){
                    c = r.getCell(serial);
                }
                if (c == null){
                    if (bAndSNumbers.get(i).get(1) == "Serial") {
                        c = r.createCell(serial, Cell.CELL_TYPE_STRING);
                    } else {
                        c = r.createCell(batch, Cell.CELL_TYPE_STRING);
                    }
                }
                c.setCellValue(bAndSNumbers.get(i).get(0));
            }
            FileOutputStream os = new FileOutputStream(fileOne);
            workbook.write(os);


        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Finished writing to Excel-sheet");
    }
}
