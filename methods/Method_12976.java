public void loadUrl(String jsUrl,CallBackFunction returnCallback){
  this.loadUrl(jsUrl);
  responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl),returnCallback);
}
