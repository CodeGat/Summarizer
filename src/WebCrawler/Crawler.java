package WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class Crawler {
    public static String crawl(String url){
        StringBuilder res = new StringBuilder();
        Elements tags = null;
        try {
            System.out.print("Connecting to "+url+"...");
            Document doc  = Jsoup.connect(url).get();
            tags = doc.getElementsByTag("p");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Element tag : Objects.requireNonNull(tags)){
            res.append(tag.text());
        }
        System.out.println("DONE.");

        return res.toString();
    }
}
