@Override public void visitNode(Context ctx,XAnnotatedMember xam,Node node,Collection<Object> result){
  try {
    result.add(xam.xao.newInstance(ctx,(Element)node));
  }
 catch (  Throwable e) {
    SofaLogger.error("visitNode error",e);
  }
}
