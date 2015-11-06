package controllers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReaderController {



    public ArrayList readFileOne(String file){
        ArrayList<ArrayList<String>> itmnumbers = new ArrayList<ArrayList<String>>();
        try {
            FileInputStream fs = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet mySheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    ArrayList<String> itm = new ArrayList<String>();
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            if(cell.getStringCellValue().startsWith("ITM")){
                                itm.add(cell.getStringCellValue());
                                itm.add(cell.getColumnIndex()+"");
                                itm.add(cell.getRowIndex()+"");
                                if (!itmnumbers.contains(itm)){
                                    itmnumbers.add(itm);
                                }
                            }
                            break;
                        default:
                    }
                }
            }
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
        return itmnumbers;

    }

    public ArrayList<ArrayList<String>> readFileTwo(ArrayList<ArrayList<String>> itms, String file){
        ArrayList<ArrayList<String>> itmnumbers = new ArrayList<ArrayList<String>>();
        try {
            FileInputStream fs = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet mySheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    ArrayList<String> itm = new ArrayList<String>();
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getStringCellValue().startsWith("ITM")){
                                for (int i = 0; i < itms.size() ; i++) {
                                    if (cell.getStringCellValue().contentEquals(itms.get(i).get(0))){
                                        itm.add(cell.getStringCellValue());
                                        itm.add(cell.getRowIndex()+"");
                                        itm.add(itms.get(i).get(2));
                                        itmnumbers.add(itm);
                                    }
                                }
                            }
                    }
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();

        }
        return itmnumbers;
    }
    public ArrayList<ArrayList<String>> findCorrectRowAndColumn(String file){
        ArrayList<ArrayList<String>> itmnumbers = new ArrayList<ArrayList<String>>();
        try {
            FileInputStream fs = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet mySheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = mySheet.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    ArrayList<String> itm = new ArrayList<String>();
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getStringCellValue().startsWith("Batch")){
                                itm.add("Batch");
                                itm.add(cell.getColumnIndex()+"");
                                itmnumbers.add(itm);
                            }
                            else if (cell.getStringCellValue().startsWith("Serial")){
                                itm.add("Serial");
                                itm.add(cell.getColumnIndex()+"");
                                itmnumbers.add(itm);
                            }
                    }
                }
            }
        } catch (Exception ioe){
            ioe.printStackTrace();
        }
        return (itmnumbers);
    }

    public ArrayList<ArrayList<String>> findBatchAndSerial(ArrayList<ArrayList<String>> batchAndSerial, ArrayList<ArrayList<String>> itm, String fileTwo){
        ArrayList<ArrayList<String>> itmnumbers = new ArrayList<ArrayList<String>>();
        try {
            FileInputStream fs = new FileInputStream(fileTwo);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet mySheet = workbook.getSheetAt(0);

            int batch = 0;
            int serial = 0;
            if (batchAndSerial.get(0).get(0) == "Batch"){
                batch = Integer.parseInt(batchAndSerial.get(0).get(1));
            }
            else if(batchAndSerial.get(0).get(0) == "Serial"){
                serial = Integer.parseInt(batchAndSerial.get(1).get(1));
            }
            if (batchAndSerial.get(1).get(0) == "Batch"){
                batch = Integer.parseInt(batchAndSerial.get(0).get(1));
            }
            else if (batchAndSerial.get(1).get(0) == "Serial"){
                serial = Integer.parseInt(batchAndSerial.get(1).get(1));
            }

            for (int i = 0; i < itm.size(); i++) {
                ArrayList<String> itms = new ArrayList<String>();
                if ((mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(batch) == null)){
                    if (mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(serial) == null){
                        continue;
                    }
                    else if(mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(serial) != null){
                        itms.add(mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(serial).toString());
                        itms.add("Serial");
                        itms.add(itm.get(i).get(2));
                        if (itms.get(0).contains(".")) {
                            itms.set(0, itms.get(0).substring(0, itms.get(0).length() - 2));
                        }
                        itmnumbers.add(itms);
                    }
                }
                else if(mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(batch) != null){
                    itms.add(mySheet.getRow(Integer.parseInt(itm.get(i).get(1))).getCell(batch).toString());
                    itms.add("Batch");
                    itms.add(itm.get(i).get(2));
                    if (itms.get(0).contains(".")){
                        itms.set(0, itms.get(0).substring(0, itms.get(0).length() - 2));
                    }
                    itmnumbers.add(itms);
                }
            }
        } catch (Exception ioe){
            ioe.printStackTrace();
        }
        return itmnumbers;
    }
}
