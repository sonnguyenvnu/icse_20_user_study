public static long getSchemaId(VertexIDType type,long count){
  checkSchemaTypeId(type,count);
  return type.addPadding(count);
}
