public int create(JSONObject payOrder){
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("payOrder",payOrder);
  String jsonParam=RpcUtil.createBaseParam(paramMap);
  Map<String,Object> result=rpcCommonService.rpcPayOrderService.create(jsonParam);
  String s=RpcUtil.mkRet(result);
  if (s == null)   return 0;
  return Integer.parseInt(s);
}
