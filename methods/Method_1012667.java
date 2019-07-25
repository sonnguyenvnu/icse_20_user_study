public void parse() throws Exception {
  SyndFeedInput input=new SyndFeedInput();
  byte b[]=downloadAndSendBinary(url);
  if (b != null) {
    SyndFeed feed=input.build(new XmlReader(new ByteArrayInputStream(b)));
    name=feed.getTitle();
    if (feed.getCategories() != null && feed.getCategories().size() > 0) {
      SyndCategory category=feed.getCategories().get(0);
      tempCategory=category.getName();
    }
    List<SyndEntry> entries=feed.getEntries();
    for (    SyndEntry entry : entries) {
      tempItemTitle=entry.getTitle();
      tempItemLink=entry.getLink();
      tempFeedLink=entry.getUri();
      tempItemThumbURL=null;
      ArrayList<Element> elements=(ArrayList<Element>)entry.getForeignMarkup();
      for (      Element elt : elements) {
        if ("group".equals(elt.getName()) && "media".equals(elt.getNamespacePrefix())) {
          List<Content> subElts=elt.getContent();
          for (          Content subelt : subElts) {
            if (subelt instanceof Element) {
              parseElement((Element)subelt,false);
            }
          }
        }
        parseElement(elt,true);
      }
      List<SyndEnclosure> enclosures=entry.getEnclosures();
      for (      SyndEnclosure enc : enclosures) {
        if (StringUtils.isNotBlank(enc.getUrl())) {
          tempItemLink=enc.getUrl();
        }
      }
      manageItem();
    }
  }
  setLastModified(System.currentTimeMillis());
}
