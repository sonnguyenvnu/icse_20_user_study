@Override public Page download(Request request,Task task){
  if (logger.isInfoEnabled()) {
    logger.info("downloading page: " + request.getUrl());
  }
  String content=getPage(request);
  if (content.contains("HTTP request failed")) {
    for (int i=1; i <= getRetryNum(); i++) {
      content=getPage(request);
      if (!content.contains("HTTP request failed")) {
        break;
      }
    }
    if (content.contains("HTTP request failed")) {
      Page page=new Page();
      page.setRequest(request);
      return page;
    }
  }
  Page page=new Page();
  page.setRawText(content);
  page.setUrl(new PlainText(request.getUrl()));
  page.setRequest(request);
  page.setStatusCode(200);
  return page;
}
