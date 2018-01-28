package com.singer568.main;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MyCrawler extends WebCrawler{
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return href.startsWith("https://www.douban.com/people/163296676/rev_contacts");
    }

    @Override
        public void visit(Page page) {
            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                String html = htmlParseData.getHtml();
                Document document = Jsoup.parse(html);
                Element content = document.getElementById("content");
                Elements dds = content.getElementsByTag("dd");
                int count = 0;
                for (Element element : dds) {
                    if (element.getElementsByTag("a").text().length() != 0) {
                        System.out.println("name: " + element.getElementsByTag("a").text() + " count: " + count++);
                    }

                }
            }
    }
}
