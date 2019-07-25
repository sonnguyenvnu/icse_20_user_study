@JmsListener(destination=MqConfig.REFUND_NOTIFY_QUEUE_NAME) public void receive(String msg){
  _log.info("??????.msg={}",msg);
  JSONObject msgObj=JSON.parseObject(msg);
  String refundOrderId=msgObj.getString("refundOrderId");
  String channelName=msgObj.getString("channelName");
  RefundOrder refundOrder=baseSelectRefundOrder(refundOrderId);
  if (refundOrder == null) {
    _log.warn("????????,????.refundOrderId={}",refundOrderId);
    return;
  }
  if (refundOrder.getStatus() != PayConstant.REFUND_STATUS_INIT) {
    _log.warn("????????({})???({}),????.refundOrderId={}",PayConstant.REFUND_STATUS_INIT,PayConstant.REFUND_STATUS_FAIL,refundOrderId);
    return;
  }
  int result=this.baseUpdateStatus4Ing(refundOrderId,"");
  if (result != 1) {
    _log.warn("????????({})??,????.refundOrderId={}",PayConstant.REFUND_STATUS_REFUNDING,refundOrderId);
    return;
  }
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("refundOrder",refundOrder);
  String jsonParam=RpcUtil.createBaseParam(paramMap);
  Map resultMap;
  if (PayConstant.CHANNEL_NAME_WX.equalsIgnoreCase(channelName)) {
    resultMap=payChannel4WxService.doWxRefundReq(jsonParam);
  }
 else   if (PayConstant.CHANNEL_NAME_ALIPAY.equalsIgnoreCase(channelName)) {
    resultMap=payChannel4AliService.doAliRefundReq(jsonParam);
  }
 else {
    _log.warn("????????,??????.refundOrderId={},channelName={}",refundOrderId,channelName);
    return;
  }
  if (!RpcUtil.isSuccess(resultMap)) {
    _log.warn("????????,??????.refundOrderId={}",refundOrderId);
    return;
  }
  Map bizResult=(Map)resultMap.get("bizResult");
  Boolean isSuccess=false;
  if (bizResult.get("isSuccess") != null)   isSuccess=Boolean.parseBoolean(bizResult.get("isSuccess").toString());
  if (isSuccess) {
    String channelOrderNo=StrUtil.toString(bizResult.get("channelOrderNo"));
    result=baseUpdateStatus4Success(refundOrderId,channelOrderNo);
    _log.info("???????????({}),refundOrderId={},????:{}",PayConstant.REFUND_STATUS_SUCCESS,refundOrderId,result);
    baseNotify4MchRefund.doNotify(refundOrder,true);
  }
 else {
    String channelErrCode=StrUtil.toString(bizResult.get("channelErrCode"));
    String channelErrMsg=StrUtil.toString(bizResult.get("channelErrMsg"));
    result=baseUpdateStatus4Fail(refundOrderId,channelErrCode,channelErrMsg);
    _log.info("???????????({}),refundOrderId={},????:{}",PayConstant.REFUND_STATUS_FAIL,refundOrderId,result);
    baseNotify4MchRefund.doNotify(refundOrder,true);
  }
}
