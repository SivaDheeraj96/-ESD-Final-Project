package com.esd.app.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class TicketPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		buildTicket(document,(List<String[]>)request.getAttribute("info") );	
		
	}
	
	public static void buildTicket(Document doc, List<String[]> values) throws DocumentException {
		doc.open();
		Paragraph p = new Paragraph("Ticket Details");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(p);
		Table t = new Table(2);
		t.setPadding(2);
		for(int i=0;i<values .size();i++) {
			System.out.println("adding row"+i);
			String[] det = values.get(i);
			for(int j=0;j<2;j++) {
				System.out.println("adding cell"+det[j]);
				t.addCell(new Cell(det[j]));
			}
		}
		doc.add(t);
		Paragraph p2 = new Paragraph("Thank You for using Our Bus Service");
		p2.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(p2);
		doc.close();
	}

	
}
