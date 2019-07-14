protected void extractAndAddRequests(Page page,boolean spawnUrl){
  if (spawnUrl && CollectionUtils.isNotEmpty(page.getTargetRequests())) {
    for (    Request request : page.getTargetRequests()) {
      addRequest(request);
    }
  }
}
