import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class 평교 {

    private static HttpURLConnection con;
    private static final String URL = "https://cle.cne.go.kr/api/srch/bookSearch.do";

    public static void main(String[] args) throws IOException {

        List<String> strings = Files.readAllLines(Paths.get("./src/main/resources/book.txt"));
//        List<String> strings = Files.readAllLines(Paths.get("C:/book.txt"));
        Iterator<String> iterator = strings.iterator();

        while(iterator.hasNext()) {
            String next = iterator.next();
            String title = null;
            title.get
            String author = null;
            if(next.contains("@")) {
                String[] split = next.split("@");
                title = split[0];
                author = split[1];
            }
            else {
                title = next;
            }


            String urlParameters = "searchTxt="+title;
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            try {
                URL myurl = new URL(URL);
                con = (HttpURLConnection) myurl.openConnection();

                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Java client");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);
                }

                StringBuilder stringBuilder;

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {

                    String line;
                    stringBuilder = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append(System.lineSeparator());
                    }
                }

                String content = stringBuilder.toString();
                if (content.contains("book-list") && content.toString().contains("\">" + title)) {
                    if(author == null)
                        System.out.println(title + " O\n");
                    else {
                        if (content.contains("<dd>" + author))
                            System.out.println(title + " O\n");
                        else
                            System.out.println(title + " X\n");
                    }
                } else {
                    System.out.println(title + " X\n");
                }
            } finally {

                con.disconnect();
            }
        }
    }

}
