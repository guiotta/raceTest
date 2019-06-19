package com.otta.raceTest.upload.converter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LapConverter {
	private static final String TEMP_FILE_PREFIX = "C://";
	private static final String TEMP_FILE_POSTFIX = "test";

	public void convert(MultipartFile multipartFile) throws IOException {
		// Files.lines(Paths.get("manifest.mf"), StandardCharsets.UTF_8).forEach(System.out::println);
        
		
		File tempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_POSTFIX);
		tempFile.deleteOnExit();
		multipartFile.transferTo(tempFile);

		FileReader reader = new FileReader(tempFile);
	    StreamTokenizer tokenizer = new StreamTokenizer(reader);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Hora", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Piloto", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Nº", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Volta", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Tempo", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Volta", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("Velocidade", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("média", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("da", tokenizer.sval);
	    
	    tokenizer.nextToken();
	    assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
	    assertEquals("volta", tokenizer.sval);
	    
	    tempFile.delete();
	    reader.close();
	}
}
