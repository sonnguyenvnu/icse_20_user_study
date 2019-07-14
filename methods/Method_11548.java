@Override public void process(Page page){
  page.addTargetRequests(page.getHtml().links().regex("https://www\\.zhihu\\.com/question/\\d+/answer/\\d+.*").all());
  page.putField("title",page.getHtml().xpath("//h1[@class='QuestionHeader-title']/text()").toString());
  page.putField("question",page.getHtml().xpath("//div[@class='QuestionRichText']//tidyText()").toString());
  page.putField("answer",page.getHtml().xpath("//div[@class='QuestionAnswer-content']/tidyText()").toString());
  if (page.getResultItems().get("title") == null) {
    page.setSkip(true);
  }
}
