package assignment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * @author ReN
 *
 */
public class Tools {

	public static ArrayList<String> getTitleKeyword(String[] HighDensityWord,
			String title) {
		// get keywords from title according to High Density Words
		System.out.println("Get title keywords...");
		HashMap<String, Integer> titleMap = new HashMap<String,Integer>();
		String[] titleWord = title.split(" ");
		int count = 1;
		for(String i:titleWord){
			for(String j:HighDensityWord){
				if(j!=null&&i.toLowerCase().contains(j.toLowerCase())) {
					if(!titleMap.containsKey(i)){
						titleMap.put(i, count);
					} else {
						titleMap.put(i, titleMap.get(i)+1);
					}
				}
					
			}
			count=1;	
		}
		ArrayList<String> TitleKeyWords = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : titleMap.entrySet()) {
			TitleKeyWords.add(entry.getKey());
		}
		//System.out.println(TitleKeyWords);
		// get keywords from title according to High Density Words
		return TitleKeyWords;
	}
	
	
	public static String getUrlKeyword(String[] HighDensityWord,String url){
		// get keywords from URL according to High Density Words
		HashMap<String, Integer> UrlMap = new HashMap<String,Integer>();
		String[] urlWords = url.split("/");

		int count = 1;
		for(String i:urlWords){
			for(String j:HighDensityWord){
				if(j!=null&&i.toLowerCase().contains(j.toLowerCase())) {
					if(!UrlMap.containsKey(i)){
						UrlMap.put(i, count);
					} else {
						UrlMap.put(i, UrlMap.get(i)+1);
					}
				}
					
			}
			count=1;
			
		}
		int max=0;
		String UrlWord = null;
		for (Map.Entry<String, Integer> entry : UrlMap.entrySet()) {
			if(entry.getValue()>max){
				max = entry.getValue();
				UrlWord = entry.getKey();
			}
		    
		}
		String newUrl = UrlWord.replaceAll("-", " ");
		return newUrl;
		
	}
	
	public static String[] wordCount(String text) throws IOException {
		// get high density words from body text
        Map<String, Word> countMap = new HashMap<String, Word>();
        System.out.println("Analyzing word density...");
        //Create BufferedReader so the words can be counted
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
       
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");
            for (String word : words) {
                if ("".equals(word)||word.length()<3) {
                    continue;
                }

                Word wordObj = countMap.get(word);
                if (wordObj == null) {
                    wordObj = new Word();
                    wordObj.word = word;
                    wordObj.count = 0;
                    countMap.put(word, wordObj);
                }

                wordObj.count++;
            }
        }

        reader.close();

        SortedSet<Word> sortedWords = new TreeSet<Word>(countMap.values());
        int i = 0,j=0;
        int maxWordsToDisplay = 20;

        String[] wordsToIgnore = {"the", "This","The", "your","and","these", 
        		"a", "to","I","this","of","for","is","his","that","with","you","after","are"};
        String[] HighDensityWord = new String[20];
        for (Word word : sortedWords) {
            if (i >= maxWordsToDisplay) { 
                break;
            }
            if (Arrays.asList(wordsToIgnore).contains(word.word)) {
                i++;
                maxWordsToDisplay++;
            } else {
            	HighDensityWord[j++] = word.word;
                i++;
            }

        }
        return HighDensityWord;
    }

    public static class Word implements Comparable<Word> {
        String word;
        int count;

        @Override
        public int hashCode() { return word.hashCode(); }

        @Override
        public boolean equals(Object obj) { return word.equals(((Word)obj).word); }

        @Override
        public int compareTo(Word b) { return b.count - count; }
    }

}
