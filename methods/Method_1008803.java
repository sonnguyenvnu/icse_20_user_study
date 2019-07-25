@JmsListener(destination=MqConfig.TRANS_NOTIFY_QUEUE_NAME) public void receive(String msg){
  _log.info("??????.msg={}",msg);
  JSONObject msgObj=JSON.parseObject(msg);
  String transOrderId=msgObj.getString("transOrderId");
  String channelName=msgObj.getString("channelName");
  TransOrder transOrder=baseSelectTransOrder(transOrderId);
  if (transOrder == null) {
    _log.warn("????????,????.transOrderId={}",transOrderId);
    return;
  }
  if (transOrder.getStatus() != PayConstant.TRANS_STATUS_INIT) {
    _log.warn("????????({})???({}),????.transOrderId={}",PayConstant.TRANS_STATUS_INIT,PayConstant.TRANS_STATUS_FAIL,transOrderId);
    return;
  }
  int result=this.baseUpdateStatus4Ing(transOrderId,"");
  if (result != 1) {
    _log.warn("????????({})??,????.transOrderId={}",PayConstant.TRANS_STATUS_TRANING,transOrderId);
    return;
  }
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("transOrder",transOrder);
  String jsonParam=RpcUtil.createBaseParam(paramMap);
  Map resultMap;
  if (PayConstant.CHANNEL_NAME_WX.equalsIgnoreCase(channelName)) {
    resultMap=payChannel4WxService.doWxTransReq(jsonParam);
  }
 else   if (PayConstant.CHANNEL_NAME_ALIPAY.equalsIgnoreCase(channelName)) {
    resultMap=payChannel4AliService.doAliTransReq(jsonParam);
  }
 else {
    _log.warn("????????,??????.transOrderId={},channelName={}",transOrderId,channelName);
    return;
  }
  if (!RpcUtil.isSuccess(resultMap)) {
    _log.warn("????????,??????.transOrderId={}",transOrderId);
    return;
  }
  Map bizResult=(Map)resultMap.get("bizResult");
  Boolean isSuccess=false;
  if (bizResult.get("isSuccess") != null)   isSuccess=Boolean.parseBoolean(bizResult.get("isSuccess").toString());
  if (isSuccess) {
    String channelOrderNo=bizResult.get("channelOrderNo") == null ? "" : bizResult.get("channelOrderNo").toString();
    result=baseUpdateStatus4Success(transOrderId,channelOrderNo);
    _log.info("???????????({}),transOrderId={},????:{}",PayConstant.TRANS_STATUS_SUCCESS,transOrderId,result);
    baseNotify4MchTrans.doNotify(transOrder,true);
  }
 else {
    String channelErrCode=bizResult.get("channelErrCode") == null ? "" : bizResult.get("channelErrCode").toString();
    String channelErrMsg=bizResult.get("channelErrMsg") == null ? "" : bizResult.get("channelErrMsg").toString();
    result=baseUpdateStatus4Fail(transOrderId,channelErrCode,channelErrMsg);
    _log.info("???????????({}),transOrderId={},????:{}",PayConstant.TRANS_STATUS_FAIL,transOrderId,result);
    baseNotify4MchTrans.doNotify(transOrder,true);
  }
}
