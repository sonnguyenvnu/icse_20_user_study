private void openWebpageUrl(String url,String anchor){
  if (openUrlReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(openUrlReqId,false);
    openUrlReqId=0;
  }
  int reqId=++lastReqId;
  closePhoto(false);
  showProgressView(true,true);
  final TLRPC.TL_messages_getWebPage req=new TLRPC.TL_messages_getWebPage();
  req.url=url;
  req.hash=0;
  openUrlReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (openUrlReqId == 0 || reqId != lastReqId) {
      return;
    }
    openUrlReqId=0;
    showProgressView(true,false);
    if (isVisible) {
      if (response instanceof TLRPC.TL_webPage && ((TLRPC.TL_webPage)response).cached_page instanceof TLRPC.TL_page) {
        addPageToStack((TLRPC.TL_webPage)response,anchor,1);
      }
 else {
        Browser.openUrl(parentActivity,req.url);
      }
    }
  }
));
}
