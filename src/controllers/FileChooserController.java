package controllers;

import javafx.event.ActionEvent;


import javafx.stage.FileChooser;

import java.io.File;


public class FileChooserController {
    public String file;
    public String file2;

    public void choseFile(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile!=null){
            file = selectedFile.getAbsolutePath();
        }
    }
    public void choseFile2(ActionEvent event) throws Exception{
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile!=null){
            file2 = selectedFile.getAbsolutePath();
        }
    }
    public void runProgram(ActionEvent event) throws Exception{
        if (file!=null &&file2!=null){
            //wf.writeFile(rf.findBatchAndSerial(rf.findCorrectRowAndColumn(), rf.readFileTwo(rf.readFileOne())), rf.findCorrectRowAndColumn2());

            ReaderController rc = new ReaderController();
            WriterController wc = new WriterController();
            wc.writeFile(rc.findBatchAndSerial(rc.findCorrectRowAndColumn(file2), rc.readFileTwo(rc.readFileOne(file),file2), file2),rc.findCorrectRowAndColumn(file),file);
        }
    }
}
