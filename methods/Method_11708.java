@Override public void process(Page page){
  List<String> requests=page.getHtml().xpath("//a[@class=\"area_link flat_btn\"]/@href").all();
  if (requests.size() > 2) {
    requests=requests.subList(0,2);
  }
  page.addTargetRequests(requests);
  page.addTargetRequests(page.getHtml().links().regex("(.*/restaurant/[^#]+)").all());
  page.putField("items",page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"name\"]/text()"));
  page.putField("prices",page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"price_outer\"]/span[@class=\"price\"]/text()"));
}
