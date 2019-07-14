private void processDistrict(Page page){
  String province=page.getRequest().getExtra("province").toString();
  String district=page.getRequest().getExtra("district").toString();
  String zipCode=page.getHtml().regex("<h2>???(\\d+)</h2>").toString();
  page.putField("result",StringUtils.join(new String[]{province,district,zipCode},"\t"));
  List<String> links=page.getHtml().links().regex("http://www\\.ip138\\.com/\\d{6}[/]?$").all();
  for (  String link : links) {
    page.addTargetRequest(new Request(link).setPriority(2).putExtra("province",province).putExtra("district",district));
  }
}
