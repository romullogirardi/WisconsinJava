package com.romullogirardi.wisconsin.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
        	
        	//Abrir PDF
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            //Adicionar título
            addTitle(document);

            //Adicionar cabeçalho
            addHeader(document);

            //Adicionar o resumo dos movimentos
            addMovementsDescription(document);
            
    		//Adicionar a tabela 1 da área de escore
            addScoresArea1(document);

            //Adicionar a tabela 2 da área de escore
            addScoresArea2(document);
            
            //Teste
            test(document);
            
         } catch (DocumentException documentException) {
             documentException.printStackTrace();
         } catch (IOException ioException) {
             ioException.printStackTrace();;
         } 
         finally {
        	 //Fechar PDF
        	 document.close();
         }
	}
	
	//////////////////////////////////////////////////////////////////////// TÍTULO //////////////////////////////////////////////////////////////////////
	private void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("RELATÓRIO - TESTE DE WISCONSIN");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}
	
	///////////////////////////////////////////////////////////////////// CABEÇALHO //////////////////////////////////////////////////////////////////////
	private void addHeader(Document document) throws DocumentException {
        document.add(new Paragraph("Nome: " + Manager.getInstance().getUserName()));
        document.add(new Paragraph("Data: " + Manager.getInstance().getTestDate()));
        document.add(new Paragraph("Duração: " + Manager.getInstance().getTestDuration()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}
	
	////////////////////////////////////////////////////////////// RESUMO DOS MOVIMENTOS /////////////////////////////////////////////////////////////////
	private void addMovementsDescription(Document document) throws DocumentException {
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
			paragraphText += (movement.isPerseverative()) ? " p" : "";
			//Pular linha
			paragraphText += "\n";
			//Guardar referências deste movimento
			previousStrategy = movement.getCurrentStrategy();
			//Adicionar linha ao relatório
			Font font = new Font(FontFamily.HELVETICA, 12f, Font.UNDERLINE);
			document.add(new Paragraph(paragraphText, font));
		}
		
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}
	
	////////////////////////////////////////////////////////////// ÁREA DE ESCORES 1 /////////////////////////////////////////////////////////////////////
	private void addScoresArea1(Document document) throws DocumentException {

		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(5);
		
		//Inserir 1ª linha (Descrição das colunas)
		table.addCell(createCell(""));
		table.addCell(createCell("Escore bruto"));
		table.addCell(createCell("Escore padrão"));
		table.addCell(createCell("Escore T"));
		table.addCell(createCell("Percentil"));

		//Inserir 2ª linha (Número de Ensaios Administrados)
		table.addCell(createCell("Número de Ensaios Administrados"));
		table.addCell(createCell(String.valueOf(Manager.getInstance().getMovements().size())));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 3ª linha (Número Total Correto)
		table.addCell(createCell("Número Total Correto"));
		table.addCell(createCell(String.valueOf(Manager.getInstance().getNumberOfRightMovements())));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 4ª linha (Número Total de Erros)
		table.addCell(createCell("Número Total de Erros"));
		table.addCell(createCell(String.valueOf(Manager.getInstance().getNumberOfWrongMovements())));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 5ª linha (Percentual de Erros)
		table.addCell(createCell("Percentual de Erros"));
		table.addCell(createCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfWrongMovements()/Manager.getInstance().getMovements().size()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 6ª linha (Respostas Perseverativas)
		table.addCell(createCell("Respostas Perseverativas"));
		table.addCell(createCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfPerseverativeMovements()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 7ª linha (Percentual de Respostas Perseverativas)
		table.addCell(createCell("Percentual de Respostas Perseverativas"));
		table.addCell(createCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfPerseverativeMovements()/Manager.getInstance().getMovements().size()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 8ª linha (Erros Perseverativos)
		table.addCell(createCell("Erros Perseverativos"));
		table.addCell(createCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfPerseverativeErrors()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 9ª linha (Percentual de Erros Perseverativos)
		table.addCell(createCell("Percentual de Erros Perseverativos"));
		table.addCell(createCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfPerseverativeErrors()/Manager.getInstance().getMovements().size()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 10ª linha (Erros Não-perseverativos)
		table.addCell(createCell("Erros Não-perseverativos"));
		table.addCell(createCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfWrongMovements() - Manager.getInstance().getNumberOfPerseverativeErrors()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 11ª linha (Percentual de Erros Não-perseverativos)
		table.addCell(createCell("Percentual de Erros Não-perseverativos"));
		table.addCell(createCell(String.valueOf(Math.round(100 * (Manager.getInstance().getNumberOfWrongMovements() - Manager.getInstance().getNumberOfPerseverativeErrors())/Manager.getInstance().getMovements().size()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 12ª linha (Respostas de Nível Conceitual)
		table.addCell(createCell("Respostas de Nível Conceitual"));
		table.addCell(createCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfConceptualLevelAnswers()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Inserir 13ª linha (Percentual de Respostas de Nível Conceitual)
		table.addCell(createCell("Percentual de Respostas de Nível Conceitual"));
		table.addCell(createCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfConceptualLevelAnswers()/Manager.getInstance().getMovements().size()))));
		table.addCell(createCell(""));
		table.addCell(createCell(""));
		table.addCell(createCell(""));

		//Adicionar a tabela ao documento
		document.add(table);
		
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}
	
	////////////////////////////////////////////////////////////// ÁREA DE ESCORES 2 /////////////////////////////////////////////////////////////////////
	private void addScoresArea2(Document document) throws DocumentException {

		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(3);
		
		//Inserir 1ª linha (Descrição das colunas)
		table.addCell(createCell(""));
		table.addCell(createCell("Escore bruto"));
		table.addCell(createCell("Percentil"));

		//Inserir 2ª linha (Número de Categorias Completadas)
		table.addCell(createCell("Número de Categorias Completadas"));
		table.addCell(createCell(String.valueOf("")));
		table.addCell(createCell(String.valueOf("")));

		//Inserir 3ª linha (Ensaios para Completar a Primeira Categoria)
		table.addCell(createCell("Ensaios para Completar a Primeira Categoria"));
		table.addCell(createCell(String.valueOf("")));
		table.addCell(createCell(String.valueOf("")));

		//Inserir 4ª linha (Fracasso em Manter o Contexto)
		table.addCell(createCell("Fracasso em Manter o Contexto"));
		table.addCell(createCell(String.valueOf("")));
		table.addCell(createCell(String.valueOf("")));

		//Inserir 5ª linha (Aprendendo a aprender)
		table.addCell(createCell("Aprendendo a aprender"));
		table.addCell(createCell(String.valueOf("")));
		table.addCell(createCell(String.valueOf("")));

		//Adicionar a tabela ao documento
		document.add(table);
		
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}

	private void test(Document document) throws DocumentException {
		
		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(3);
		
		//Definir tabelas de cada coluna
		PdfPTable table1 = new PdfPTable(1);
		table1.addCell(createCell("[1][1]"));
		table1.addCell(createCell("[2][1]"));
		table1.addCell(createCell("[3][1]"));
		PdfPCell pdfPCellTable1 = new PdfPCell(table1);
		pdfPCellTable1.setPadding(0);
		table.addCell(pdfPCellTable1);

		PdfPTable table2 = new PdfPTable(1);
		table2.addCell(createCell("[1][2]"));
		table2.addCell(createCell("[2][2]"));
		table2.addCell(createCell("[3][2]"));
		PdfPCell pdfPCellTable2 = new PdfPCell(table2);
		pdfPCellTable2.setPadding(0);
		table.addCell(pdfPCellTable2);
		
		PdfPTable table3 = new PdfPTable(1);
		table3.addCell(createCell("[1][3]"));
		table3.addCell(createCell("[2][3]"));
		table3.addCell(createCell("[3][3]"));
		PdfPCell pdfPCellTable3 = new PdfPCell(table3);
		pdfPCellTable3.setPadding(0);
		table.addCell(pdfPCellTable3);

		//Adicionar a tabela ao documento
		document.add(table);
	}
	private PdfPCell createCell(String text) {
	
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		return cell;
	}
	
	public void showPDFFile() {
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(pdfFilePath);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException e) {
		        System.out.println("Este computador não possui um leitor de PDF instalado");
		    }
		}
	}
}