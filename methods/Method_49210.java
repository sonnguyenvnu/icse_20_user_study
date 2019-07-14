@Override public ParameterIndexField[] getFieldKeys(){
  ParameterIndexField[] result=fields;
  if (result == null) {
    Iterable<SchemaSource.Entry> entries=base.getRelated(TypeDefinitionCategory.INDEX_FIELD,Direction.OUT);
    int numFields=Iterables.size(entries);
    result=new ParameterIndexField[numFields];
    int pos=0;
    for (    SchemaSource.Entry entry : entries) {
      assert entry.getSchemaType() instanceof PropertyKey;
      assert entry.getModifier() instanceof Parameter[];
      result[pos++]=ParameterIndexField.of((PropertyKey)entry.getSchemaType(),(Parameter[])entry.getModifier());
    }
    fields=result;
  }
  assert result != null;
  return result;
}
