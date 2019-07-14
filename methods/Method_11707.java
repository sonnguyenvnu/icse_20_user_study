@Override public void process(Page page){
  List<Selectable> nodes=page.getHtml().xpath("//ul[@id=ma-thumb-list]/li").nodes();
  StringBuilder accum=new StringBuilder();
  for (  Selectable node : nodes) {
    accum.append("img:").append(node.xpath("//a/@href").get()).append("\n");
    accum.append("title:").append(node.xpath("//img/@alt").get()).append("\n");
  }
  page.putField("",accum.toString());
  if (accum.length() == 0) {
    page.setSkip(true);
  }
  page.addTargetRequests(page.getHtml().links().regex("http://www\\.mama\\.cn/photo/.*\\.html").all());
}
