protected String getCommonInvocationLog(String start,MethodInvocation invocation,long startTime){
  String appStart="";
  if (StringUtils.hasText(appStart)) {
    appStart="-" + start;
  }
  long endTime=System.currentTimeMillis();
  StringBuilder sb=new StringBuilder("SOFA-Reference" + appStart + "(");
  sb.append(invocation.getMethod().getName()).append(",");
  for (  Object o : invocation.getArguments()) {
    sb.append(o);
    sb.append(",");
  }
  sb.append((endTime - startTime)).append("ms").append(")");
  return sb.toString();
}
