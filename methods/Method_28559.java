private boolean hitLinkResult(WebView.HitTestResult result){
  return result.getType() == WebView.HitTestResult.SRC_ANCHOR_TYPE || result.getType() == HitTestResult.IMAGE_TYPE || result.getType() == HitTestResult.SRC_IMAGE_ANCHOR_TYPE;
}
