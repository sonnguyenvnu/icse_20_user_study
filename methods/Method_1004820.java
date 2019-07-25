@Override public void convert(Object from,BytesArray to){
  Assert.isTrue(from instanceof PigTuple,String.format("Unexpected object type, expecting [%s], given [%s]",PigTuple.class,from.getClass()));
  PigTuple pt=(PigTuple)from;
  ResourceFieldSchema schema=pt.getSchema();
  ResourceSchema tupleSchema=schema.getSchema();
  if (tupleSchema == null) {
    to.bytes("{}");
    return;
  }
  ResourceFieldSchema[] fields=tupleSchema.getFields();
  Assert.isTrue(fields.length == 1,"When using JSON input, only one field is expected");
  Object object;
  byte type;
  try {
    object=pt.getTuple().get(0);
    type=pt.getTuple().getType(0);
  }
 catch (  Exception ex) {
    throw new EsHadoopIllegalStateException("Encountered exception while processing tuple",ex);
  }
  if (type == DataType.BIGCHARARRAY || type == DataType.CHARARRAY) {
    to.bytes(object.toString());
    return;
  }
  if (type == DataType.BYTEARRAY) {
    DataByteArray dba=(DataByteArray)object;
    to.bytes(dba.get(),dba.size());
    return;
  }
  throw new EsHadoopIllegalArgumentException(String.format("Cannot handle Pig type [%s]; expecting [%s,%s]",object.getClass(),String.class,DataByteArray.class));
}
