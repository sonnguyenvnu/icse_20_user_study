@Override public void process(Page page){
  List<String> requests=page.getHtml().links().regex("(http://www\\.diaoyuweng\\.com/home\\.php\\?mod=space&uid=88304&do=thread&view=me&type=thread&order=dateline&from=space&page=\\d+)").all();
  page.addTargetRequests(requests);
  requests=page.getHtml().links().regex("(http://www\\.diaoyuweng\\.com/thread-\\d+-1-1.html)").all();
  page.addTargetRequests(requests);
  if (page.getUrl().toString().contains("thread")) {
    page.putField("title",page.getHtml().xpath("//a[@id='thread_subject']"));
    page.putField("content",page.getHtml().xpath("//div[@class='pcb']//tbody/tidyText()"));
    page.putField("date",page.getHtml().regex("??? (\\d{4}-\\d+-\\d+ \\d+:\\d+:\\d+)"));
    page.putField("id",new PlainText("1000" + page.getUrl().regex("http://www\\.diaoyuweng\\.com/thread-(\\d+)-1-1.html").toString()));
  }
}
