public CrawlstartDocument store(Index index) throws IOException {
  String crawlid=getCrawlID();
  index.add(GridIndex.CRAWLSTART_INDEX_NAME,GridIndex.EVENT_TYPE_NAME,crawlid,this);
  return this;
}
