@Override public void process(Page page){
  page.putField("name",page.getHtml().css("dl.lemmaWgt-lemmaTitle h1","text").toString());
  page.putField("description",page.getHtml().xpath("//div[@class='lemma-summary']/allText()"));
}
