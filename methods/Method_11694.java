@Override public void process(Page page){
  List<String> requests=page.getHtml().links().regex("(.*/post/.*)").all();
  page.addTargetRequests(requests);
  page.putField("title",page.getHtml().xpath("//title").regex("(.*?)\\|").toString());
  page.putField("content",page.getHtml().smartContent());
  page.putField("date",page.getUrl().regex("post/(\\d+-\\d+-\\d+)/"));
  page.putField("id",page.getUrl().regex("post/\\d+-\\d+-\\d+/(\\d+)"));
}
