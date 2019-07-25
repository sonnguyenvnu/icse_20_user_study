public JSONObject query(String mchId,String transOrderId,String mchTransNo,String executeNotify){
  Map<String,Object> paramMap=new HashMap<>();
  Map<String,Object> result;
  if (StringUtils.isNotBlank(transOrderId)) {
    paramMap.put("mchId",mchId);
    paramMap.put("transOrderId",transOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcTransOrderService.selectByMchIdAndTransOrderId(jsonParam);
  }
 else {
    paramMap.put("mchId",mchId);
    paramMap.put("mchTransNo",mchTransNo);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcTransOrderService.selectByMchIdAndMchTransNo(jsonParam);
  }
  String s=RpcUtil.mkRet(result);
  if (s == null)   return null;
  boolean isNotify=Boolean.parseBoolean(executeNotify);
  JSONObject payOrder=JSONObject.parseObject(s);
  if (isNotify) {
    paramMap=new HashMap<>();
    paramMap.put("transOrderId",transOrderId);
    String jsonParam=RpcUtil.createBaseParam(paramMap);
    result=rpcCommonService.rpcNotifyPayService.sendBizPayNotify(jsonParam);
    s=RpcUtil.mkRet(result);
    _log.info("??????,???????????.????:{}",s);
  }
  return payOrder;
}
