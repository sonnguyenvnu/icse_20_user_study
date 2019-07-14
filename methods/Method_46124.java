protected String fillProtocolAndVersion(SubscribeServiceResult subscribeServiceResult,String targetURL,String serviceName){
  final List<String> datas=subscribeServiceResult.getDatas();
  if (datas == null) {
    targetURL=targetURL + ":" + MeshConstants.TCP_PORT;
  }
 else {
    for (    String data : subscribeServiceResult.getDatas()) {
      String param=data.substring(data.indexOf("?"));
      targetURL=targetURL + ":" + MeshConstants.TCP_PORT;
      targetURL=targetURL + param;
      break;
    }
  }
  return targetURL;
}
