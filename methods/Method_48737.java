private static void checkSchemaTypeId(VertexIDType type,long count){
  Preconditions.checkArgument(VertexIDType.Schema.is(type.suffix()),"Expected schema vertex but got: %s",type);
  Preconditions.checkArgument(type.isProper(),"Expected proper type but got: %s",type);
  Preconditions.checkArgument(count > 0 && count < SCHEMA_COUNT_BOUND,"Invalid id [%s] for type [%s] bound: %s",count,type,SCHEMA_COUNT_BOUND);
}
