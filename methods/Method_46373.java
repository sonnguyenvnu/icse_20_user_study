private void generateServerErrorContext(Map<String,String> context,SofaRequest request,SofaTracerSpan serverSpan){
  Map<String,String> tagsWithStr=serverSpan.getTagsWithStr();
  context.put("serviceName",tagsWithStr.get(RpcSpanTags.SERVICE));
  context.put("methodName",tagsWithStr.get(RpcSpanTags.METHOD));
  context.put("protocol",tagsWithStr.get(RpcSpanTags.PROTOCOL));
  context.put("invokeType",tagsWithStr.get(RpcSpanTags.INVOKE_TYPE));
  context.put("callerUrl",tagsWithStr.get(RpcSpanTags.REMOTE_IP));
  context.put("callerApp",tagsWithStr.get(RpcSpanTags.REMOTE_APP));
  context.put("callerZone",tagsWithStr.get(RpcSpanTags.REMOTE_ZONE));
  context.put("callerIdc",tagsWithStr.get(RpcSpanTags.REMOTE_IDC));
  if (request != null) {
    context.put("paramTypes",com.alipay.common.tracer.core.utils.StringUtils.arrayToString(request.getMethodArgSigs(),'|',"",""));
  }
}
