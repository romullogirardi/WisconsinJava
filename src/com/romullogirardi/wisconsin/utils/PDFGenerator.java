package com.romullogirardi.wisconsin.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.romullogirardi.wisconsin.model.Enums.Strategy;
import com.romullogirardi.wisconsin.model.Manager;
import com.romullogirardi.wisconsin.model.Movement;

public class PDFGenerator {

	//ATTRIBUTE
	private String pdfFilePath;
	
	//CONSTRUCTOR
	public PDFGenerator(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}
	
	//OTHER METHODS
	public void generateAndShowPDFFile() {
		
		generatePDFFile();
		showPDFFile();
	}
	
	private void generatePDFFile() {

 		Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            Paragraph title = new Paragraph("RELATÓRIO - TESTE DE WISCONSIN");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Nome: " + Manager.getInstance().getUserName()));
            document.add(new Paragraph("Data: " + Manager.getInstance().getTestDate()));
            document.add(new Paragraph("Duração: " + Manager.getInstance().getTestDuration()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Strategy previousStrategy = null;
            int repeatedSuccessCounter = 0;
    		for(int index = 0; index < Manager.getInstance().getMovements().size(); index++) {
    			
    			Movement movement = Manager.getInstance().getMovements().get(index);
    			String paragraphText = "";
    			//Colocar linha divisória e o indicador de categoria, caso necessário
    			if(!movement.getCurrentStrategy().equals(previousStrategy)) {
        			if(previousStrategy != null) {
        				paragraphText += (!movement.getCurrentStrategy().equals(previousStrategy)) ? "__________________\n" : "";
        				repeatedSuccessCounter = 0;
        			}
        			paragraphText += movement.getCurrentStrategy().getFirstLetter() + "    ";
    			}
    			//Senão, inserir espaço vazio
    			else {
        			paragraphText += "      ";
    			}
    			//Inserir o índice de acerto sublinhado
    			if(movement.isPreviousSuccess() && movement.isSuccess()) {
    				repeatedSuccessCounter++;
    			}
    			else if(!movement.isPreviousSuccess() && movement.isSuccess()) {
    				repeatedSuccessCounter = 1;
    			}
    			else {
    				repeatedSuccessCounter = 0;
    			}
    			paragraphText += ((repeatedSuccessCounter != 0) ? String.valueOf(repeatedSuccessCounter) : "  ") + "    ";
    			//Inserir o índice do movimento
    			paragraphText += (index + 1) + ".    ";
    			//Inserir os indicadores de estratégia
    			paragraphText += movement.getReportDescription();
    			//Inserir indicador de perseveratividade
    			paragraphText += (movement.isPerseverative()) ? "p" : "";
    			//Pular linha
    			paragraphText += "\n";
    			//Guardar referências deste movimento
    			previousStrategy = movement.getCurrentStrategy();
    			//Adicionar linha ao relatório
    			document.add(new Paragraph(paragraphText));
    		}

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
    		document.add(new Paragraph("Número de acertos: " + Manager.getInstance().getNumberOfRightMovements()));
    		document.add(new Paragraph("Número de erros: " + Manager.getInstance().getNumberOfWrongMovements()));
         } catch (DocumentException documentException) {
             documentException.printStackTrace();
         } catch (IOException ioException) {
             ioException.printStackTrace();;
         } 
         finally {
        	 document.close();
         }
	}
	
//	private void generatePDFFile() {
//
// 		Document document = new Document();
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
//            document.open();
//
//            Paragraph title = new Paragraph("RELATÓRIO - TESTE DE WISCONSIN");
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//
//            document.add(new Paragraph("Nome: " + Manager.getInstance().getUserName()));
//            document.add(new Paragraph("Data: " + Manager.getInstance().getTestDate()));
//            document.add(new Paragraph("Duração: " + Manager.getInstance().getTestDuration()));
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//
//			PdfPTable table = new PdfPTable(6);
//			table.setTotalWidth(6 * 88);
//			table.setLockedWidth(true);
//			table.addCell(createCell("Movimento"));
//			table.addCell(createCell("Estratágia corrente"));
//			table.addCell(createCell("Resultado"));
//			table.addCell(createCell("Mesma cor"));
//			table.addCell(createCell("Mesma forma"));
//			table.addCell(createCell("Mesmo número"));
//
//    		for(int index = 0; index < Manager.getInstance().getMovements().size(); index++) {
//    			
//    			Movement movement = Manager.getInstance().getMovements().get(index);
//    			table.addCell(createCell(String.valueOf(index + 1)));
//    			table.addCell(createCell(movement.getCurrentStrategy().toString()));
//    			table.addCell(createCell((movement.isSuccess()) ? "CERTO" : "ERRADO"));
//    			table.addCell(createCell((movement.isColorSuccess()) ? "X" : ""));
//    			table.addCell(createCell((movement.isShapeSuccess()) ? "X" : ""));
//    			table.addCell(createCell((movement.isNumberSuccess()) ? "X" : ""));
//    		}
//			document.add(table);
//
//            document.add(new Paragraph(" "));
//            document.add(new Paragraph(" "));
//    		document.add(new Paragraph("Número de acertos: " + Manager.getInstance().getNumberOfRightMovements()));
//    		document.add(new Paragraph("Número de erros: " + Manager.getInstance().getNumberOfWrongMovements()));
//         } catch (DocumentException documentException) {
//             documentException.printStackTrace();
//         } catch (IOException ioException) {
//             ioException.printStackTrace();;
//         } 
//         finally {
//        	 document.close();
//         }
//	}
//
//	private PdfPCell createCell(String text) {
//		
//		PdfPCell cell = new PdfPCell(new Phrase(text));
//		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//		return cell;
//	}
	
	public void showPDFFile() {
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(pdfFilePath);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException e) {
		        System.out.println("Este computador n�o possui um leitor de PDF instalado");
		    }
		}
	}
}