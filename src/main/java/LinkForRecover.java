import com.google.api.services.gmail.Gmail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Roman on 11/9/15.
 */
public class LinkForRecover {
    private  static String hypperLink;

    public static String getLink () throws IOException, MessagingException {

        Gmail service = GmailGetMessage.getGmailService();

        // Print the labels in the user's account.
        String user = "me";

        String id = GmailGetMessage.listMessagesMatchingQuery(service, user, "from:Fluix").get(0).getId();

        System.out.println(id);


        String line;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(GmailGetMessage.getMimeMessage(service, user, id).getInputStream()));
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();

//        System.out.println(buffer);

        Document doc = Jsoup.parse(buffer.toString());

        Elements resultLinks = doc.select("a");


        for (Element link : resultLinks) {

            if(link.text().equals("this link")){
                hypperLink = link.attr("href").substring(5).replace("=/","/").replace("=2E",".").replace("=26","&").replace("=3D","=").replace("=25","%").replace("i=l","il").replace("=22","");

            }

        }

        return hypperLink;

    }
}
