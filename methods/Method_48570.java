private StaticBuffer getIndexKey(CompositeIndexType index,Object[] values){
  final DataOutput out=serializer.getDataOutput(8 * DEFAULT_OBJECT_BYTELEN + 8);
  VariableLong.writePositive(out,index.getID());
  final IndexField[] fields=index.getFieldKeys();
  Preconditions.checkArgument(fields.length > 0 && fields.length == values.length);
  for (int i=0; i < fields.length; i++) {
    final IndexField f=fields[i];
    final Object value=values[i];
    Preconditions.checkNotNull(value);
    if (AttributeUtil.hasGenericDataType(f.getFieldKey())) {
      out.writeClassAndObject(value);
    }
 else {
      assert value.getClass().equals(f.getFieldKey().dataType()) : value.getClass() + " - " + f.getFieldKey().dataType();
      out.writeObjectNotNull(value);
    }
  }
  StaticBuffer key=out.getStaticBuffer();
  if (hashKeys)   key=HashingUtil.hashPrefixKey(hashLength,key);
  return key;
}
