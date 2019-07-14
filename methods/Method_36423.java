@SuppressWarnings("unchecked") @Override protected Object getValue(Context ctx,Element base) throws Exception {
  ArrayList<Object> values=new ArrayList<Object>();
  if (xao != null) {
    DOMHelper.visitNodes(ctx,this,base,path,elementListVisitor,values);
  }
 else {
    if (path.attribute != null) {
      DOMHelper.visitNodes(ctx,this,base,path,attributeVisitor,values);
    }
 else {
      DOMHelper.visitNodes(ctx,this,base,path,elementVisitor,values);
    }
  }
  if (type != ArrayList.class) {
    if (type.isArray()) {
      if (componentType.isPrimitive()) {
        return PrimitiveArrays.toPrimitiveArray(values,componentType);
      }
 else {
        return values.toArray((Object[])Array.newInstance(componentType,values.size()));
      }
    }
 else {
      Collection col=(Collection)type.newInstance();
      col.addAll(values);
      return col;
    }
  }
  return values;
}
