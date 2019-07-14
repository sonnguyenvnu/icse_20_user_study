@Override public void visitNode(Context ctx,XAnnotatedMember xam,Node node,String key,Map<String,Object> result){
  String val=node.getNodeValue();
  if (val != null && val.length() > 0) {
    if (xam.trim)     val=val.trim();
    Object object=XMapSpringUtil.getSpringObject(((XAnnotatedMapSpring)xam).componentType,val,((XAnnotatedMapSpring)xam).getXaso().getApplicationContext());
    if (object != null)     result.put(key,object);
  }
}
