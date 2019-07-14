@Override public void process(Page page){
  int i=Integer.valueOf(page.getUrl().regex("shop/(\\d+)").toString()) + 1;
  page.addTargetRequest("http://kaichiba.com/shop/" + i);
  page.putField("title",page.getHtml().xpath("//Title"));
  page.putField("items",page.getHtml().xpath("//li[@class=\"foodTitle\"]").replace("^\\s+","").replace("\\s+$","").replace("<span>.*?</span>",""));
}
