@Override public void process(Page page){
  List<String> strings=page.getHtml().links().regex(".*/yewu/.*").all();
  page.addTargetRequests(strings);
  page.putField("title",page.getHtml().regex("<title>(.*)</title>"));
  page.putField("body",page.getHtml().xpath("//dd"));
}
