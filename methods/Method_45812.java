private String getServiceKey(SofaRequest request){
  StringBuilder builder=new StringBuilder();
  builder.append(request.getTargetAppName()).append("#").append(request.getMethodName());
  return builder.toString();
}
