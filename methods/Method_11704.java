@Override public void process(Page page){
  page.addTargetRequests(page.getHtml().links().regex("http://www\\.infoq\\.com/cn/minibooks/.*").all());
  List<String> all=page.getHtml().links().regex(".*\\.pdf").all();
  if (CollectionUtils.isNotEmpty(all)) {
    page.putField("pdf",all);
  }
 else {
    page.getResultItems().setSkip(true);
  }
}
