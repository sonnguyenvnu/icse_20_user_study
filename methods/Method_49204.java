@Override public IndexField[] getFieldKeys(){
  IndexField[] result=fields;
  if (result == null) {
    Iterable<SchemaSource.Entry> entries=base.getRelated(TypeDefinitionCategory.INDEX_FIELD,Direction.OUT);
    int numFields=Iterables.size(entries);
    result=new IndexField[numFields];
    for (    SchemaSource.Entry entry : entries) {
      Integer value=ParameterType.INDEX_POSITION.findParameter((Parameter[])entry.getModifier(),null);
      Preconditions.checkNotNull(value);
      int pos=value;
      Preconditions.checkArgument(pos >= 0 && pos < numFields,"Invalid field position: %s",pos);
      assert entry.getSchemaType() instanceof PropertyKey;
      result[pos]=IndexField.of((PropertyKey)entry.getSchemaType());
    }
    fields=result;
  }
  assert result != null;
  return result;
}
