@Override public void visitNode(Context ctx,XAnnotatedMember xam,Node node,String key,Map<String,Object> result){
  String val=node.getNodeValue();
  if (xam.valueFactory != null) {
    result.put(key,xam.valueFactory.getValue(ctx,val));
  }
 else {
    result.put(key,val);
  }
}
