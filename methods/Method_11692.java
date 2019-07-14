public void process(Page page){
  Html html=page.getHtml();
  List<String> questionList=html.xpath("//table[@class='tgCustomerCommunityCenterColumn']//div[@class='content']//table[@class='dataGrid']//tr").all();
  if (questionList != null && questionList.size() > 1) {
    for (int i=1; i < questionList.size(); i++) {
      System.out.println(questionList.get(i));
      Html tempHtml=Html.create("<table>" + questionList.get(i) + "</table>");
      String comment=tempHtml.xpath("//td[@class='title']//a/text()").toString();
      System.out.println(comment);
      String answerNum=tempHtml.xpath("//td[@class='num']/text()").toString();
      System.out.println(answerNum);
      String createTime=tempHtml.xpath("//td[3]/text()").toString();
      System.out.println(createTime);
    }
  }
}
