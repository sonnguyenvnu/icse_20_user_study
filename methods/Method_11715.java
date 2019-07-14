@Override public void process(Page page){
  if (page.getUrl().regex(URL_LIST).match()) {
    page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
    page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
  }
 else {
    page.putField("title",page.getHtml().xpath("//div[@class='articalTitle']/h2"));
    page.putField("content",page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
    page.putField("date",page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
  }
}
