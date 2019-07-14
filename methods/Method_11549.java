@Override public void process(Page page){
  List<String> requests=page.getHtml().links().regex(urlPattern).all();
  page.addTargetRequests(requests);
  page.putField("title",page.getHtml().xpath("//title"));
  page.putField("html",page.getHtml().toString());
  page.putField("content",page.getHtml().smartContent());
}
