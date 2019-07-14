@Override public void process(Page page){
  page.addTargetRequests(page.getHtml().links().regex(".*yanghaoli\\.iteye\\.com/blog/\\d+").all());
  page.putField("title",page.getHtml().xpath("//title").toString());
  page.putField("content",page.getHtml().smartContent().toString());
}
