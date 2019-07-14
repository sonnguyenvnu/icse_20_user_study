@Override public void process(Page page){
  List<String> requests=page.getHtml().links().regex(".*article.*").all();
  page.addTargetRequests(requests);
  page.putField("title",page.getHtml().xpath("//div[@class='clearfix neirong']//h1/text()"));
  page.putField("content",page.getHtml().xpath("//div[@id='neirong_box']/tidyText()"));
}
