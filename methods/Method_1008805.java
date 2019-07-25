public JSONObject query(String mchId,String payOrderId,String mchOrderNo,String executeNotify){
  Map<String,Object> paramMap=new HashMap<>();
  Map<String,Object> result;
  if (StringUtils.isNotBlank(payOrderId)) {
    paramMap.put("mchId",mchId);
    paramMap.put("payOrderId",payOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcPayOrderService.selectByMchIdAndPayOrderId(jsonParam);
  }
 else {
    paramMap.put("mchId",mchId);
    paramMap.put("mchOrderNo",mchOrderNo);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcPayOrderService.selectByMchIdAndMchOrderNo(jsonParam);
  }
  String s=RpcUtil.mkRet(result);
  if (s == null)   return null;
  boolean isNotify=Boolean.parseBoolean(executeNotify);
  JSONObject payOrder=JSONObject.parseObject(s);
  if (isNotify) {
    paramMap=new HashMap<>();
    paramMap.put("payOrderId",payOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcNotifyPayService.sendBizPayNotify(jsonParam);
    s=RpcUtil.mkRet(result);
    _log.info("??????,???????????.????:{}",s);
  }
  return payOrder;
}
