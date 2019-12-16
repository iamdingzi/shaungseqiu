import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void execute() throws IOException {
        //todo 修改路径
        File file = new File("C:\\workspace\\ideaProjects\\oras\\spider\\src\\main\\resources\\static\\demo.html");
        Document doc = Jsoup.parse(file,"UTF-8");
        Elements datas = doc.select("#tdata");
        Element table = datas.get(0);
        Elements trElemens = table.getElementsByTag("tr");
        for (Element tr : trElemens) {
            if (tr.hasAttr("class")) {
                continue;
            }
            Elements tds = tr.getElementsByTag("td");
            for (Element td : tds) {
                String className = td.className();
                if (td.attributes().hasKey("align")) {
                    String sequenceNumber = td.text();
                    System.out.println(sequenceNumber);//期号
                    continue;
                }
                if (className.contains("chartBall07") || className.contains("chartBall01")) {
                    String redBall = td.text();
                    System.out.print(redBall +" ");//红球
                } else if (className.contains("chartBall02")) {
                    String blueBall = td.text();
                    System.out.println("blue:"+ blueBall);//篮球
                }
            }
            System.out.println();
        }
    }
}
