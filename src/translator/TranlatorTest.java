package translator;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TranlatorTest {

	@Test
	void emptyTranslator() {
		Translator t = new Translator();
		assertEquals(0, t.getNumbersOfWords());
		
		String translate = t.translateWord("Ciao");
		assertEquals("Ciao", translate);
	}
	
	@Test
	void translateOneWord() {
		Translator t = new Translator();
		t.addTranslation("Ciao","Hello");
		assertEquals(1, t.getNumbersOfWords());
		
		String translate = t.translateWord("Ciao");
		assertEquals("Hello", translate);
	}
	
	@Test
	void translateTwoWord() {
		Translator t = new Translator();
		t.addTranslation("Ciao","Hello");
		t.addTranslation("Salve","Hello");

		assertEquals(2, t.getNumbersOfWords());
		
		String translation1 = t.translateWord("Ciao");
		assertEquals("Hello", translation1);
		String translation2 = t.translateWord("Salve");
		assertEquals("Hello", translation2);
	}
	
	@Test
	void twoTranslationForTheSameWord() {
		Translator t = new Translator();
		t.addTranslation("Ciao","Hello");
		t.addTranslation("Ciao","Hi");

		assertEquals(1, t.getNumbersOfWords());
		
		String translation = t.translateWord("Ciao");
		assertEquals("Hello, Hi", translation);
	}
	
	@Test
	void translateASentence() {
		Translator t = new Translator();
		t.addTranslation("Ciao","Hello");
		t.addTranslation("Signore","Sir");

		
		String translation = t.translateSentence("Ciao Signore");
		assertEquals("Hello Sir", translation);
	}
	
	@Test
	void translateASentenceWithSameWordWith2Translation() {
		Translator t = new Translator();
		t.addTranslation("Ciao","Hello");
		t.addTranslation("Ciao","Hi");
		t.addTranslation("Signore","Sir");

		
		String translation = t.translateSentence("Ciao Signore");
		assertEquals("Hello Sir", translation);
	}
	
	
	@Test
	void translatingSentence() {
		Translator t = new Translator();
		t.ReadFileToTranslate("TestFile");
		String sen = t.TranslateSentence("hi attal");
		assertEquals("ciao attal",sen);
	}
	
	
	@Test
	void readFileForTranslation() {
		Translator t = new Translator();
		int fileWord = t.ReadFileToTranslate("TestFile");
		assertEquals(2,fileWord);
	}
	
	
	@Test
	void checkFileFormat() {
		Translator t = new Translator();
		boolean isFile = t.CheckFileFormat("TestFile");
		assertEquals(true, isFile);
	}
	
	
	
	@Test
	void uppercaseFirstLetter() {
		Translator t = new Translator();
		t.ReadFileToTranslate("TestFile");
		String str = t.uppercaseTranslateText("hi attal");
		assertEquals("Ciao attal",str);
	}

		
	@Test
	void punctuationControl () {
		Translator t = new Translator();
		t.ReadFileToTranslate("TestFile");
		String str = t.punctuationControl("hi .attal");
		assertEquals("Ciao .attal",str);
	}


}
