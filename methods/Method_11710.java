@Override public void process(Page page){
  List<String> requests=page.getHtml().regex("<a[^<>]*href=[\"']{1}(http://17dujingdian\\.com/post/[^#]*?)[\"']{1}").all();
  page.addTargetRequests(requests);
  page.putField("title",page.getHtml().xpath("//div[@id='content']//h2/a"));
  page.putField("content",page.getHtml().smartContent());
}
