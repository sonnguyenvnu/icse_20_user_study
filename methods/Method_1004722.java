@Override public void convert(Object from,BytesArray to){
  Assert.isTrue(from instanceof HiveType,String.format("Unexpected object type, expecting [%s], given [%s]",HiveType.class,from.getClass()));
  HiveType ht=(HiveType)from;
  ObjectInspector oi=ht.getObjectInspector();
  Assert.isTrue(Category.STRUCT == oi.getCategory(),String.format("Unexpected object category, expecting [%s], given [%s]",Category.STRUCT,oi.getTypeName()));
  StructObjectInspector soi=(StructObjectInspector)oi;
  List<? extends StructField> refs=soi.getAllStructFieldRefs();
  Assert.isTrue(refs.size() == 1,"When using JSON input, only one field is expected");
  StructField structField=refs.get(0);
  ObjectInspector foi=structField.getFieldObjectInspector();
  Assert.isTrue(Category.PRIMITIVE == foi.getCategory(),String.format("Unexpected object category, expecting [%s], given [%s]",Category.PRIMITIVE,oi.getTypeName()));
  Object writable=((PrimitiveObjectInspector)foi).getPrimitiveWritableObject(soi.getStructFieldData(ht.getObject(),structField));
  if (writable != null && HiveConstants.VARCHAR_WRITABLE.equals(writable.getClass().getName())) {
    to.bytes(writable.toString());
    return;
  }
  super.convert(writable,to);
}
