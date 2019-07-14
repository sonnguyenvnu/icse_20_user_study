@Override public void serializeValue(final JsonContext jsonContext,final Object value){
  jsonContext.writeOpenObject();
  BeanSerializer beanVisitor=new BeanSerializer(jsonContext,value);
  beanVisitor.serialize();
  jsonContext.writeCloseObject();
}
