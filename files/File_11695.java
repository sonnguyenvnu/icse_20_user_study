package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class DiandianBlogProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        //a()表示�??�?�链接，links()表示�??�?�所有链接
        //getHtml()返回Html对象，支�?链�?调用
        //r()表示用正则表达�?�??�?�一�?�内容，regex()表示�??�?�多�?�内容
        //toString()表示�?��?��?�结果，all()表示�?�多�?�
        List<String> requests = page.getHtml().links().regex("(.*/post/.*)").all();
        //使用page.addTargetRequests()方法将待抓�?�的链接加入队列
        page.addTargetRequests(requests);
        //page.putField(key,value)将抽�?�的内容加入结果Map
        //x()和xs()使用xpath进行抽�?�
        page.putField("title", page.getHtml().xpath("//title").regex("(.*?)\\|").toString());
        //smartContent()使用readability技术直接抽�?�正文，对于规整的文本有比较好的抽�?�正确率
        page.putField("content", page.getHtml().smartContent());
        page.putField("date", page.getUrl().regex("post/(\\d+-\\d+-\\d+)/"));
        page.putField("id", page.getUrl().regex("post/\\d+-\\d+-\\d+/(\\d+)"));
    }

    @Override
    public Site getSite() {
        //site定义抽�?��?置，以�?�开始url等
        if (site == null) {
            site = Site.me().setDomain("progressdaily.diandian.com").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }
        return site;
    }
}
