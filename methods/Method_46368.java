private void generateClientErrorContext(Map<String,String> context,SofaRequest request,SofaTracerSpan clientSpan){
  Map<String,String> tagsWithStr=clientSpan.getTagsWithStr();
  context.put("serviceName",tagsWithStr.get(RpcSpanTags.SERVICE));
  context.put("methodName",tagsWithStr.get(RpcSpanTags.METHOD));
  context.put("protocol",tagsWithStr.get(RpcSpanTags.PROTOCOL));
  context.put("invokeType",tagsWithStr.get(RpcSpanTags.INVOKE_TYPE));
  context.put("targetUrl",tagsWithStr.get(RpcSpanTags.REMOTE_IP));
  context.put("targetApp",tagsWithStr.get(RpcSpanTags.REMOTE_APP));
  context.put("targetZone",tagsWithStr.get(RpcSpanTags.REMOTE_ZONE));
  context.put("targetIdc",tagsWithStr.get(RpcSpanTags.REMOTE_IDC));
  context.put("paramTypes",com.alipay.common.tracer.core.utils.StringUtils.arrayToString(request.getMethodArgSigs(),'|',"",""));
  context.put("targetCity",tagsWithStr.get(RpcSpanTags.REMOTE_CITY));
  context.put("uid",tagsWithStr.get(RpcSpanTags.USER_ID));
}
