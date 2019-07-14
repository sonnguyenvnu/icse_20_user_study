@Override public void visitNode(Context ctx,XAnnotatedMember xam,Node node,String key,Map<String,Object> result){
  try {
    result.put(key,xam.xao.newInstance(ctx,(Element)node));
  }
 catch (  Exception e) {
    SofaLogger.error("visitNode error",e);
  }
}
