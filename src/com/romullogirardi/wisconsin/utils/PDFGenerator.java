package com.romullogirardi.wisconsin.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
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
        	PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            //Adicionar título
            addTitle(document);

            //Adicionar cabeçalho
            addHeader(document);

            //Adicionar a sequência de categorias
            addCategoriesSequence(document);
            
            //Adicionar a tabela com o resumo dos movimentos
            addMovementsDescriptionTable(document, pdfWriter);
            
    		//Adicionar a tabela 1 da área de escore
            addScoresArea1(document);

            //Adicionar a tabela 2 da área de escore
            addScoresArea2(document);
            
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
	}
	
	///////////////////////////////////////////////////////////////////// CABEÇALHO //////////////////////////////////////////////////////////////////////
	private void addHeader(Document document) throws DocumentException {
        document.add(new Paragraph("Nome: " + Manager.getInstance().getUserName()));
        document.add(new Paragraph("Data: " + Manager.getInstance().getTestDate()));
        document.add(new Paragraph("Duração: " + Manager.getInstance().getTestDuration()));
        document.add(new Paragraph(" "));
	}
	
	///////////////////////////////////////////////////////////// SEQUÊNCIA DE CATEGORIAS /////////////////////////////////////////////////////////////////
	private void addCategoriesSequence(Document document) throws DocumentException {
		
		//Ler o número total de categorias completadas
		int completedCategoriesNumber = Manager.getInstance().getCategorySequenceNumber() - 1;
		
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.add("SEQUÊNCIA DE CATEGORIAS: ");
		Chunk colorChunk1 = new Chunk(Strategy.COLOR.getFirstLetter());
		if(completedCategoriesNumber > 0)
			colorChunk1.setUnderline(2.5f, 4);
		paragraph.add(colorChunk1);
		Chunk shapeChunk1 = new Chunk(Strategy.SHAPE.getFirstLetter());
		if(completedCategoriesNumber > 1)
			shapeChunk1.setUnderline(2.5f, 4);
		paragraph.add("  ");
		paragraph.add(shapeChunk1);
		Chunk numberChunk1 = new Chunk(Strategy.NUMBER.getFirstLetter());
		if(completedCategoriesNumber > 2)
			numberChunk1.setUnderline(2.5f, 4);
		paragraph.add("  ");
		paragraph.add(numberChunk1);
		Chunk colorChunk2 = new Chunk(Strategy.COLOR.getFirstLetter());
		if(completedCategoriesNumber > 3)
			colorChunk2.setUnderline(2.5f, 4);
		paragraph.add("  ");
		paragraph.add(colorChunk2);
		Chunk shapeChunk2 = new Chunk(Strategy.SHAPE.getFirstLetter());
		if(completedCategoriesNumber > 4)
			shapeChunk2.setUnderline(2.5f, 4);
		paragraph.add("  ");
		paragraph.add(shapeChunk2);
		Chunk numberChunk2 = new Chunk(Strategy.NUMBER.getFirstLetter());
		if(completedCategoriesNumber > 5)
			numberChunk2.setUnderline(2.5f, 4);
		paragraph.add("  ");
		paragraph.add(numberChunk2);
		
		document.add(paragraph);
	}
	
	////////////////////////////////////////////////////////////// RESUMO DOS MOVIMENTOS /////////////////////////////////////////////////////////////////
	private void addMovementsDescriptionTable(Document document, PdfWriter pdfWriter) throws DocumentException {
    
		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(4);
		
		//Inicializar variáveis de controle
        Paragraph paragraph;
        PdfPCell cellColumn1, cellColumn2, cellColumn3, cellColumn4;
        Paragraph paragraphColumn1 = new Paragraph();
        Paragraph paragraphColumn2 = new Paragraph();
        Paragraph paragraphColumn3 = new Paragraph();
        Paragraph paragraphColumn4 = new Paragraph();
        
        //Varrer todos os movimentos e preencher tabela
		for(int index = 0; index < Manager.getInstance().getMovements().size(); index++) {

			//Guardar referência para o movimento atual
			Movement movement = Manager.getInstance().getMovements().get(index);

			//Selecionar coluna do movimento
			if(index <= 15)
				paragraph = paragraphColumn1;
			else if(index <= 31)
				paragraph = paragraphColumn2;
			else if(index <= 47)
				paragraph = paragraphColumn3;
			else 
				paragraph = paragraphColumn4;
			
			//Setar a fonte do texto
			paragraph.setFont(new Font(FontFamily.HELVETICA, 10));
			
			//Colocar linha divisória e o indicador de categoria, caso necessário
			Strategy previousStrategy = (movement.getPreviousMovement() != null) ? movement.getPreviousMovement().getCurrentStrategy() : null;
			if(!movement.getCurrentStrategy().equals(previousStrategy)) {
    			if(previousStrategy != null) {
    				paragraph.add(((!movement.getCurrentStrategy().equals(previousStrategy)) ? "______________________\n" : ""));
    			}
    			paragraph.add(movement.getCurrentStrategy().getFirstLetter() + "    ");
			}
			//Senão, inserir espaço vazio
			else {
    			paragraph.add("      ");
			}
			//Inserir o índice de acerto sublinhado
			int repeatedSuccessCounter = movement.getRepeatedSuccessCounter();
			Chunk repeatedSuccessCounterChunk = (repeatedSuccessCounter != 0) ? new Chunk(" " + String.valueOf(repeatedSuccessCounter) + " ") : new Chunk("    ");
			repeatedSuccessCounterChunk.setUnderline(1.0f, -1.5f);
			paragraph.add(repeatedSuccessCounterChunk);
			paragraph.add("    ");
			//Inserir o índice do movimento
			if(!movement.isSuccess())
				paragraph.add("(");
			paragraph.add((index + 1) + ".  ");
			//Inserir os indicadores de estratégia
			Chunk colorChunk = new Chunk(Strategy.COLOR.getFirstLetter());
			if(movement.isColorSuccess())
				colorChunk.setUnderline(2.0f, 3);
			paragraph.add(colorChunk);
			Chunk shapeChunk = new Chunk(Strategy.SHAPE.getFirstLetter());
			if(movement.isShapeSuccess())
				shapeChunk.setUnderline(2.0f, 3);
			paragraph.add("  ");
			paragraph.add(shapeChunk);
			Chunk numberChunk = new Chunk(Strategy.NUMBER.getFirstLetter());
			if(movement.isNumberSuccess())
				numberChunk.setUnderline(2.0f, 3);
			paragraph.add("  ");
			paragraph.add(numberChunk);
			Chunk otherChunk = new Chunk(Strategy.OTHER.getFirstLetter());
			if(movement.isOtherSuccess())
				otherChunk.setUnderline(2.0f, 3);
			paragraph.add("  ");
			paragraph.add(otherChunk);
			if(!movement.isSuccess())
				paragraph.add(")");
			//Inserir indicador de perseveratividade
			paragraph.add((movement.isPerseverative()) ? "  p" : "");
			//Guardar referências deste movimento
			previousStrategy = movement.getCurrentStrategy();
			//Adicionar parágrafo à tabela ou pular linha
			paragraph.add("\n\n\n");
			if(index == 15) {
				cellColumn1 = createParagraphCell(paragraph);
				cellColumn1.setBorder(Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
				table.addCell(cellColumn1);
			}
			else if(index == 31) {
				cellColumn2 = createParagraphCell(paragraph);
				cellColumn2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
				table.addCell(cellColumn2);
			}
			else if(index == 47) {
				cellColumn3 = createParagraphCell(paragraph);
				cellColumn3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
				table.addCell(cellColumn3);
			}
			else if(index == 63) {
				cellColumn4 = createParagraphCell(paragraph);
				cellColumn4.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
				table.addCell(cellColumn4);
			}
		}
		
		document.add(table);
		
        document.add(new Paragraph(" "));
	}
	
	////////////////////////////////////////////////////////////// ÁREA DE ESCORES 1 /////////////////////////////////////////////////////////////////////
	private void addScoresArea1(Document document) throws DocumentException {

		//Apresentar título da área de escores
		Paragraph paragraph = new Paragraph("ÁREA DE ESCORE");
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		
		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new int[] {4, 1, 1, 1, 1});
		table.setSpacingBefore(4);
		
		//Inserir 1ª linha (Descrição das colunas)
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell("Escore bruto"));
		table.addCell(createPhraseCell("Escore padrão"));
		table.addCell(createPhraseCell("Escore T"));
		table.addCell(createPhraseCell("Percentil"));

		//Inserir 2ª linha (Número de Ensaios Administrados)
		table.addCell(createPhraseCell("Número de Ensaios Administrados"));
		table.addCell(createPhraseCell(String.valueOf(Manager.getInstance().getMovements().size())));
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());

		//Inserir 3ª linha (Número Total Correto)
		table.addCell(createPhraseCell("Número Total Correto"));
		table.addCell(createPhraseCell(String.valueOf(Manager.getInstance().getNumberOfRightMovements())));
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());

		//Inserir 4ª linha (Número Total de Erros)
		table.addCell(createPhraseCell("Número Total de Erros"));
		table.addCell(createPhraseCell(String.valueOf(Manager.getInstance().getNumberOfWrongMovements())));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 5ª linha (Percentual de Erros)
		table.addCell(createPhraseCell("Percentual de Erros"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfWrongMovements()/Manager.getInstance().getMovements().size()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 6ª linha (Respostas Perseverativas)
		table.addCell(createPhraseCell("Respostas Perseverativas"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfPerseverativeMovements()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 7ª linha (Percentual de Respostas Perseverativas)
		table.addCell(createPhraseCell("Percentual de Respostas Perseverativas"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfPerseverativeMovements()/Manager.getInstance().getMovements().size()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 8ª linha (Erros Perseverativos)
		table.addCell(createPhraseCell("Erros Perseverativos"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfPerseverativeErrors()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 9ª linha (Percentual de Erros Perseverativos)
		table.addCell(createPhraseCell("Percentual de Erros Perseverativos"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfPerseverativeErrors()/Manager.getInstance().getMovements().size()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 10ª linha (Erros Não-perseverativos)
		table.addCell(createPhraseCell("Erros Não-perseverativos"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfWrongMovements() - Manager.getInstance().getNumberOfPerseverativeErrors()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 11ª linha (Percentual de Erros Não-perseverativos)
		table.addCell(createPhraseCell("Percentual de Erros Não-perseverativos"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * (Manager.getInstance().getNumberOfWrongMovements() - Manager.getInstance().getNumberOfPerseverativeErrors())/Manager.getInstance().getMovements().size()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Inserir 12ª linha (Respostas de Nível Conceitual)
		table.addCell(createPhraseCell("Respostas de Nível Conceitual"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(Manager.getInstance().getNumberOfConceptualLevelAnswers()))));
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());
		table.addCell(createGrayCell());

		//Inserir 13ª linha (Percentual de Respostas de Nível Conceitual)
		table.addCell(createPhraseCell("Percentual de Respostas de Nível Conceitual"));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfConceptualLevelAnswers()/Manager.getInstance().getMovements().size()))));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell(""));

		//Adicionar a tabela ao documento
		document.add(table);
		
        document.add(new Paragraph(" "));
	}
	
	////////////////////////////////////////////////////////////// ÁREA DE ESCORES 2 /////////////////////////////////////////////////////////////////////
	private void addScoresArea2(Document document) throws DocumentException {

		//Definir número de colunas da tabela
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(new int[] {2, 1, 1});
		
		//Inserir 1ª linha (Descrição das colunas)
		table.addCell(createPhraseCell(""));
		table.addCell(createPhraseCell("Escore bruto"));
		table.addCell(createPhraseCell("Percentil"));

		//Inserir 2ª linha (Número de Categorias Completadas)
		table.addCell(createPhraseCell("Número de Categorias Completadas"));
		table.addCell(createPhraseCell(String.valueOf(Manager.getInstance().getCategorySequenceNumber() - 1)));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * (Manager.getInstance().getCategorySequenceNumber() - 1) / 6))));

		//Inserir 3ª linha (Ensaios para Completar a Primeira Categoria)
		table.addCell(createPhraseCell("Ensaios para Completar a Primeira Categoria"));
		table.addCell(createPhraseCell(String.valueOf(Manager.getInstance().getNumberOfTriesToCompleteFirstCategory())));
		table.addCell(createPhraseCell(String.valueOf(Math.round(100 * Manager.getInstance().getNumberOfTriesToCompleteFirstCategory() / Manager.getInstance().getMovements().size()))));

		//Inserir 4ª linha (Fracasso em Manter o Contexto)
		table.addCell(createPhraseCell("Fracasso em Manter o Contexto"));
		table.addCell(createPhraseCell(String.valueOf("")));
		table.addCell(createPhraseCell(String.valueOf("")));

		//Inserir 5ª linha (Aprendendo a aprender)
		table.addCell(createPhraseCell("Aprendendo a aprender"));
		table.addCell(createPhraseCell(String.valueOf("")));
		table.addCell(createPhraseCell(String.valueOf("")));

		//Adicionar a tabela ao documento
		document.add(table);
		
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
	}

	private PdfPCell createParagraphCell(Paragraph paragraph) {
		
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private PdfPCell createPhraseCell(String text) {
		
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		return cell;
	}
	
	private PdfPCell createGrayCell() {
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.GRAY);
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