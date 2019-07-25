public CrawlerDocument store(Index index) throws IOException {
  if (index == null)   return this;
  String url=getURL();
  if (url != null && url.length() > 0) {
    String id=Digest.encodeMD5Hex(url);
    index.add(GridIndex.CRAWLER_INDEX_NAME,GridIndex.EVENT_TYPE_NAME,id,this);
  }
 else {
    assert false : "url not set / store";
  }
  return this;
}
