package com.example.dentisttestapp;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;

public class ResultController {
    @FXML
    public Label name;

    @FXML
    public Label date;

    @FXML
    public Label resultLabel;

    @FXML
    public Label description;

    @FXML
    public JFXButton printBtn;

    final String[] resultsArray = new String[]{"Низкая вероятность развития болезней периодонта. " +
            "Стоматологическая помощь осуществляется в соответствии с клиническими протоколами, диспансерное наблюдение " +
            "и вызов пациента на профилактические осмотры 1 раз в 6 месяцев",
            "Средняя вероятность развития болезней периодонта. " +
            "Стоматологическая помощь осуществляется в соответствии с клиническими протоколами, " +
            "диспансерное наблюдение и вызов пациента на профилактические осмотры 1 раз в 3 месяца",
            "Высокая вероятность развития болезней периодонта. " +
            "Стоматологическая помощь осуществляется в соответствии с клиническими протоколами, " +
            "диспансерное наблюдение и вызов пациента на профилактические осмотры 1 раз в 1-3 месяца"
    };

    public void initData(int result, String nameStr){
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyy");
        date.setText(date.getText() + formatForDateNow.format(new Date()));
        resultLabel.setText(String.valueOf(result));

        if(result <= 9){
            description.setText(resultsArray[0]);
        }else{
            if(result < 20){
                description.setText(resultsArray[1]);
            }else{
                description.setText(resultsArray[2]);
            }
        }

        if(nameStr.length() > 0){
            name.setText(nameStr);
        }

    }


    @FXML
    protected void onPrintButtonClick() throws DocumentException, IOException {
        String PATH = "D:/Результаты тестирования";
        File dir = new File(PATH);
        if (!dir.exists()) dir.mkdirs();


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(PATH + "/" + name.getText() + ".pdf"));

        document.open();

        BaseFont baseFont = BaseFont.createFont("microsoftsansserif.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        addParagraph(document, baseFont, 20, Font.BOLD, name.getText());
        addParagraph(document, baseFont, 16, Font.BOLD, "Результаты расчета:");
        addParagraph(document, baseFont, 14, Font.NORMAL, date.getText());
        addParagraph(document, baseFont, 16, Font.BOLD, "Вероятность развития болезней периодонта X = " + resultLabel.getText());
        addParagraph(document, baseFont, 14, Font.NORMAL, description.getText());

        document.close();
        File pdfFile = new File(PATH + "/" + name.getText() + ".pdf");

        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }
        }
    }

    public void addParagraph(Document document, BaseFont baseFont, int fontSize, int fontParam, String text) throws DocumentException {
        Font font = new Font(baseFont, fontSize, fontParam);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.add(new Paragraph(" "));
        document.add(paragraph);
    }
}
