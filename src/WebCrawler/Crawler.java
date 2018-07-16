package WebCrawler;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;

public class Crawler {
    @NotNull
    public static String crawl(String url){
        StringBuilder res = new StringBuilder();
        Elements tags = null;
        boolean defaulted = false;

        try {
            System.out.print("Connecting to "+url+"...");
            Document doc  = Jsoup.connect(url).get();
            if      (url.contains("bbc"))        tags = doc.select(".story-body__inner p");
            else if (url.contains("cnn"))        tags = doc.select(".l-container p");
            else if (url.contains("foxnews"))    tags = doc.select(".article-body p");
            else if (url.contains("news.com"))   tags = doc.select("div#story p");
            else if (url.contains("abc.net.au")) tags = doc.select(".article p").select(":not(.topics)");
            else {
                defaulted = true;
                tags = doc.getElementsByTag("p");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Element tag : Objects.requireNonNull(tags)){
            res.append(tag.text());
        }
        System.out.println("DONE"+ (defaulted ? "(Using default Crawler)." : "."));

        return res.toString();
    }
}
