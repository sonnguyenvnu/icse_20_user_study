@SuppressWarnings("unchecked") @Override protected Object getValue(Context ctx,Element base) throws Exception {
  ArrayList<Object> values=new ArrayList<Object>();
  if (path.attribute != null) {
    DOMHelper.visitNodes(ctx,this,base,path,attributeVisitor,values);
  }
 else {
    DOMHelper.visitNodes(ctx,this,base,path,elementVisitor,values);
  }
  if (type != ArrayList.class) {
    if (type.isArray() && !componentType.isPrimitive()) {
      values.toArray((Object[])Array.newInstance(componentType,values.size()));
    }
 else {
      Collection col=(Collection)type.newInstance();
      col.addAll(values);
      return col;
    }
  }
  return values;
}
