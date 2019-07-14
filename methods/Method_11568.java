private String getValue(Element element){
  if (attrName == null) {
    return element.outerHtml();
  }
 else   if ("innerHtml".equalsIgnoreCase(attrName)) {
    return element.html();
  }
 else   if ("text".equalsIgnoreCase(attrName)) {
    return getText(element);
  }
 else   if ("allText".equalsIgnoreCase(attrName)) {
    return element.text();
  }
 else {
    return element.attr(attrName);
  }
}
