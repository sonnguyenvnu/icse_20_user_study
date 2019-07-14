@Override public void visitNode(Context ctx,XAnnotatedMember xam,Node node,Collection<Object> result){
  String val=node.getNodeValue();
  if (val != null && val.length() > 0) {
    if (xam.trim)     val=val.trim();
    Object object=XMapSpringUtil.getSpringObject(((XAnnotatedListSpring)xam).componentType,val,((XAnnotatedListSpring)xam).getXaso().getApplicationContext());
    if (object != null)     result.add(object);
  }
}
