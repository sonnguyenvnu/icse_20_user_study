private void processProvince(Page page){
  List<String> districts=page.getHtml().xpath("//body/table/tbody/tr[@bgcolor=\"#ffffff\"]").all();
  Pattern pattern=Pattern.compile("<td>([^<>]+)</td>.*?href=\"(.*?)\"",Pattern.DOTALL);
  for (  String district : districts) {
    Matcher matcher=pattern.matcher(district);
    while (matcher.find()) {
      String title=matcher.group(1);
      String link=matcher.group(2);
      Request request=new Request(link).setPriority(1).putExtra("province",page.getRequest().getExtra("province")).putExtra("district",title);
      page.addTargetRequest(request);
    }
  }
}
