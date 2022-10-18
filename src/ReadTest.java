import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadTest {
	
	
 
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://www.news.com.au/content-feeds/latest-news-national/");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
        	
            System.out.println("\033[1m" + message.title);
            System.out.println(message.getPubDate());
            System.out.println(message.description);
            System.out.println("read more: " + message.link);
            System.out.println("");
            System.out.println("");
            
        }

    }
}