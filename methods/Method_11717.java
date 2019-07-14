@Override public void process(Page page){
  List<String> relativeUrl=page.getHtml().xpath("//li[@class='item clearfix']/div/a/@href").all();
  page.addTargetRequests(relativeUrl);
  relativeUrl=page.getHtml().xpath("//div[@id='zh-question-related-questions']//a[@class='question_link']/@href").all();
  page.addTargetRequests(relativeUrl);
  List<String> answers=page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div").all();
  boolean exist=false;
  for (  String answer : answers) {
    String vote=new Html(answer).xpath("//div[@class='zm-votebar']//span[@class='count']/text()").toString();
    if (Integer.valueOf(vote) >= voteNum) {
      page.putField("vote",vote);
      page.putField("content",new Html(answer).xpath("//div[@class='zm-editable-content']"));
      page.putField("userid",new Html(answer).xpath("//a[@class='author-link']/@href"));
      exist=true;
    }
  }
  if (!exist) {
    page.setSkip(true);
  }
}
