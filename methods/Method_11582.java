@Override public List<String> selectList(Element element){
  Elements elements=element.select("a");
  List<String> links=new ArrayList<String>(elements.size());
  for (  Element element0 : elements) {
    if (!StringUtil.isBlank(element0.baseUri())) {
      links.add(element0.attr("abs:href"));
    }
 else {
      links.add(element0.attr("href"));
    }
  }
  return links;
}
