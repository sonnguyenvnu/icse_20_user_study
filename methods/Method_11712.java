private void processCountry(Page page){
  List<String> provinces=page.getHtml().xpath("//*[@id=\"newAlexa\"]/table/tbody/tr/td").all();
  for (  String province : provinces) {
    String link=xpath("//@href").select(province);
    String title=xpath("/text()").select(province);
    Request request=new Request(link).setPriority(0).putExtra("province",title);
    page.addTargetRequest(request);
  }
}
