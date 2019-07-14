@Override public void process(Page page){
  List<String> requests=page.getHtml().regex("<a[^<>]*href=(bbstcon\\?board=Pictures&file=[^>]*)").all();
  page.addTargetRequests(requests);
  page.putField("title",page.getHtml().xpath("//div[@id='content']//h2/a"));
  page.putField("content",page.getHtml().smartContent());
}
