package translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
	
	
	Map<String, String> translations= new HashMap<String, String>();

	public int getNumbersOfWords() {
		return translations.size();
	}

	public String translateWord(String word) {
		if(!translations.containsKey(word)) {
			return word;
		}
		return translations.get(word);
	}

	public void addTranslation(String word, String translation) {
		if(translations.containsKey(word)) {
			translation= translations.get(word)+", "+translation;
		}
		this.translations.put(word, translation);
	}

	public String translateSentence(String sentence) {
		String[] words= sentence.split(" ");
		String translatedSentence = "";
		for(String word:words) {
			 String translation= firstTranslation(word);
			translatedSentence += translation+ " ";
		}
		return translatedSentence.trim();	
		
		
	}

	private String firstTranslation(String word) {
		String translation = translateWord(word);
		if(translation.indexOf(",") > 0)
			translation=translation.substring(0, translation.indexOf(","));
		return translation;
	}
	
	
	public int ReadFileToTranslate(String path) {
		int readline = 0;	
		  if(CheckFileFormat(path)) {
		    try {
		      
		      File myFile = new File(path);
		      Scanner scanner = new Scanner(myFile);
		      while (scanner.hasNextLine()) {
		    	readline++;
		        String data = scanner.nextLine();
		        String[] key_val = data.split(":");
		        
		        addTranslation(key_val[0], key_val[1]);
		      }
		      scanner.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("There is error.");
		      e.printStackTrace();
		    }
		  }else {
			  return 0;
		}
		  return readline;
	  }
	
	 public boolean CheckFileFormat(String path) {
		    try {
		      File file = new File(path);
		      int badFormat = 0;
		      Scanner scanner = new Scanner(file);
		      while (scanner.hasNextLine()) {
		        String data = scanner.nextLine();
		        if(!data.contains(":") ){
		        	badFormat++;
		        }else {
					String[] b = data.split(":");
					if(b.length > 2)
						badFormat++;
				}
		      }
		      scanner.close();
		      if(badFormat != 0)
		    	  return false;
		      return true;
		    } catch (FileNotFoundException e) {
		      System.out.println("There is error.");
		      e.printStackTrace();
		      return false;
		    }
		  }
	 
	 
	  public String twoWord(String word) {
		  String trn = "";
		  if(word.contains(",")) {
			  String[] ww = word.split(",");
			  trn = ww[0];
			  for(int i = 1;i<ww.length;i++) {
					 trn = trn + "[" + ww[i] + "]";
			  }
		  }
		  else {
			return word;
		}
		  return trn;
	}
	  
	 public String TranslateSentence(String text) {
		  String trn = "";
		  String[] wordtoword = text.split(" ");
		  trn = twoWord(translateWord(wordtoword[0]));
		  
		  for(int i = 1;i<wordtoword.length;i++) {
			 trn = trn + " " + twoWord(translateWord(wordtoword[i]));
		  }
		  System.out.println(trn);
		  return trn;
	}
	 
	  
	 public String uppercaseTranslateText(String text) {
		  String trn = "";
		  String[] wordtoword = text.split(" ");
		  trn = translateWord(wordtoword[0]);
		  trn = twoWord(translateWord(wordtoword[0]));
		  trn = trn.substring(0, 1).toUpperCase() + trn.substring(1);
		  for(int i = 1;i<wordtoword.length;i++) {
			  trn = trn + " " + twoWord(translateWord(wordtoword[i]));
		  }
		  System.out.println(trn);
		  return trn;
	}
	 
	 public String punctuationControl(String text) {
		  String trn = "";
		  String[] wordtoword = text.split(" ");
		  
		  trn = removePunctuation(wordtoword[0]);
		  
		  trn = trn.substring(0, 1).toUpperCase() + trn.substring(1);
		  for(int i = 1;i<wordtoword.length;i++) {
			 trn = trn + " " + removePunctuation(wordtoword[i]);
		  }
		  System.out.println(trn);
		  return trn;
	}
	 
	 public String removePunctuation(String word) {
		  
		  String[] dot = {".","?","\"","!","#","$","%",";","'","\\"};
		  int start = 0;
		  boolean isTran = false;
		  String temp = "";

		  for(int j = 0 ;j < word.length();j++){
			  char ch =  word.charAt(j);
			  String s ="";
			  String ss = String.valueOf(ch);
			  for(int i=0;i<dot.length;i++) {
				  
				  if(ss.equals(dot[i])) {
					  
					  int end = j;
					  if(end < word.length()) {
						  s = word.substring(start,end);
					  }else {
						  s = word.substring(start);
					  }
					  if(!s.equals(translateWord(s))) {
						  s = twoWord(translateWord(s)) + dot[i];
						  temp = temp + s;
						  isTran = true;
					  }
					  start =j+1;
					  continue;
				  }
			  }
		  }
		  if(!isTran) {
			  return twoWord(translateWord(word));
		  }
		  return temp;
	}
	 
	 
	

}
