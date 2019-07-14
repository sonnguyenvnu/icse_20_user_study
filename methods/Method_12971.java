/** 
 * ???CallBackFunction data????????????
 * @param url
 */
void handlerReturnData(String url){
  String functionName=BridgeUtil.getFunctionFromReturnUrl(url);
  CallBackFunction f=responseCallbacks.get(functionName);
  String data=BridgeUtil.getDataFromReturnUrl(url);
  if (f != null) {
    f.onCallBack(data);
    responseCallbacks.remove(functionName);
    return;
  }
}
