package assignment;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author ReN
 *
 */
public class WebCrawler {

	public static void main(String[] args) throws IOException {
		if (args.length==0 ) {
			System.out.println("Please input an URL.");
			return;
		}
		try{
			String url = args[0];
			//connect and parse HTML
			Document doc = Jsoup.connect(url).timeout(30000).get();
			String bodyText = doc.body().text()+doc.select("p").text()+doc.select("article").text();
			String heading = doc.select("h1").text();
			//initial page
			WebInfo page = new WebInfo();
			
			//Get High Density Words 
			page.setHighDensityWord(Tools.wordCount(bodyText));
			
			
			//ULR filter
			page.setUrlWord(Tools.getUrlKeyword(page.getHighDensityWord(), url));
			
			
			//Heading filter or title filter
			page.setTitleKeyword(Tools.getTitleKeyword(page.getHighDensityWord(),heading));

			//Get final result and print out 
			
			String FinalResult = page.getFinalResult();
			System.out.println("Final result is: "+ FinalResult);
			return;
		}catch (IOException iox) {
			System.out.println("Please input an valid URL.");
			return;
        }
	}
 
	
}
