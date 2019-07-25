@JmsListener(destination=MqConfig.MCH_REFUND_NOTIFY_QUEUE_NAME) public void receive(String msg){
  String logPrefix="????????";
  _log.info("{}????:msg={}",logPrefix,msg);
  JSONObject msgObj=JSON.parseObject(msg);
  String respUrl=msgObj.getString("url");
  String orderId=msgObj.getString("orderId");
  int count=msgObj.getInteger("count");
  if (StringUtils.isEmpty(respUrl)) {
    _log.warn("{}????URL??,respUrl={}",logPrefix,respUrl);
    return;
  }
  String httpResult=httpPost(respUrl);
  int cnt=count + 1;
  _log.info("{}notifyCount={}",logPrefix,cnt);
  if ("success".equalsIgnoreCase(httpResult)) {
    try {
      int result=baseService4RefundOrder.baseUpdateStatus4Complete(orderId);
      _log.info("{}??payOrderId={},?????????->{}",logPrefix,orderId,result == 1 ? "??" : "??");
    }
 catch (    Exception e) {
      _log.error(e,"?????????????");
    }
    try {
      int result=super.baseUpdateMchNotifySuccess(orderId,httpResult,(byte)cnt);
      _log.info("{}??????,orderId={},result={},notifyCount={},??:{}",logPrefix,orderId,httpResult,cnt,result == 1 ? "??" : "??");
    }
 catch (    Exception e) {
      _log.error(e,"??????????");
    }
    return;
  }
 else {
    try {
      int result=super.baseUpdateMchNotifyFail(orderId,httpResult,(byte)cnt);
      _log.info("{}??????,orderId={},result={},notifyCount={},??:{}",logPrefix,orderId,httpResult,cnt,result == 1 ? "??" : "??");
    }
 catch (    Exception e) {
      _log.error(e,"??????????");
    }
    if (cnt > 5) {
      _log.info("{}????notifyCount()>5,????",respUrl,cnt);
      return;
    }
    msgObj.put("count",cnt);
    this.send(mchRefundNotifyQueue,msgObj.toJSONString(),cnt * 60 * 1000);
    _log.info("{}????????,????:{},{}??????",respUrl,cnt,cnt * 60);
  }
}
