public JSONObject query(String mchId,String refundOrderId,String mchRefundNo,String executeNotify){
  Map<String,Object> paramMap=new HashMap<>();
  Map<String,Object> result;
  if (StringUtils.isNotBlank(refundOrderId)) {
    paramMap.put("mchId",mchId);
    paramMap.put("refundOrderId",refundOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcRefundOrderService.selectByMchIdAndRefundOrderId(jsonParam);
  }
 else {
    paramMap.put("mchId",mchId);
    paramMap.put("mchRefundNo",mchRefundNo);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcRefundOrderService.selectByMchIdAndMchRefundNo(jsonParam);
  }
  String s=RpcUtil.mkRet(result);
  if (s == null)   return null;
  boolean isNotify=Boolean.parseBoolean(executeNotify);
  JSONObject payOrder=JSONObject.parseObject(s);
  if (isNotify) {
    paramMap=new HashMap<>();
    paramMap.put("refundOrderId",refundOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcNotifyPayService.sendBizPayNotify(jsonParam);
    s=RpcUtil.mkRet(result);
    _log.info("??????,???????????.????:{}",s);
  }
  return payOrder;
}
