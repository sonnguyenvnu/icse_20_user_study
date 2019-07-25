private static void replace(Elements elements,String attrName,String domain){
  Iterator<Element> iterator=elements.iterator();
  while (iterator.hasNext()) {
    Element element=iterator.next();
    if (element.hasAttr("cdn-exclude")) {
      continue;
    }
    String url=element.attr(attrName);
    if (StrUtil.isBlank(url) || !url.startsWith("/") || url.startsWith("//")) {
      continue;
    }
    url=domain + url;
    element.attr(attrName,url);
  }
}
