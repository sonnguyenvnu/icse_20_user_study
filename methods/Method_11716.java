@Override public void process(Page page){
  List<String> strings=page.getHtml().regex("<a[^<>]*href=[\"']{1}(/post-free.*?\\.shtml)[\"']{1}").all();
  page.addTargetRequests(strings);
  page.putField("title",page.getHtml().xpath("//div[@id='post_head']//span[@class='s_title']//b"));
  page.putField("body",page.getHtml().smartContent());
}
