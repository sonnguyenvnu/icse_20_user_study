@Override public LoggerDefine parse(MethodInterceptorHolder holder){
  Api api=holder.findAnnotation(Api.class);
  ApiOperation operation=holder.findAnnotation(ApiOperation.class);
  String action="";
  if (api != null) {
    action=action.concat(api.value());
  }
  if (null != operation) {
    action=StringUtils.isEmpty(action) ? operation.value() : action + "-" + operation.value();
  }
  return new LoggerDefine(action,"");
}
