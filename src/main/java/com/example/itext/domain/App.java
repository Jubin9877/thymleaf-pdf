package com.example.itext.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class App {
  
  public static void main( String[] args ) throws DocumentException, IOException
  {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("XHTML");
    
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    
    Context context = new Context();
    context.setVariable("name", "Jubin");
    
    String html = templateEngine.process("templates/index", context);
    // step 1
    Document document = new Document();
      // step 2
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
      // step 3
      document.open();
//      XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//          new FileInputStream("index.html")); 
      // step 4
      XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
//              new FileInputStream("index.html")); 
      //step 5
       document.close();

      System.out.println( "PDF Created!" );
  }

}
