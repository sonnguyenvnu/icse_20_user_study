@Override public Map select(String jsonParam){
  BaseParam baseParam=JsonUtil.getObjectFromJson(jsonParam,BaseParam.class);
  Map<String,Object> bizParamMap=baseParam.getBizParamMap();
  if (ObjectValidUtil.isInvalid(bizParamMap)) {
    _log.warn("???????????????, {}. jsonParam={}",RetEnum.RET_PARAM_NOT_FOUND.getMessage(),jsonParam);
    return RpcUtil.createFailResult(baseParam,RetEnum.RET_PARAM_NOT_FOUND);
  }
  String refundOrderId=baseParam.isNullValue("refundOrderId") ? null : bizParamMap.get("refundOrderId").toString();
  if (ObjectValidUtil.isInvalid(refundOrderId)) {
    _log.warn("???????????????, {}. jsonParam={}",RetEnum.RET_PARAM_INVALID.getMessage(),jsonParam);
    return RpcUtil.createFailResult(baseParam,RetEnum.RET_PARAM_INVALID);
  }
  RefundOrder refundOrder=super.baseSelectRefundOrder(refundOrderId);
  if (refundOrder == null)   return RpcUtil.createFailResult(baseParam,RetEnum.RET_BIZ_DATA_NOT_EXISTS);
  String jsonResult=JsonUtil.object2Json(refundOrder);
  return RpcUtil.createBizResult(baseParam,jsonResult);
}
