public InternalRelationType getBaseType(){
  Entry entry=Iterables.getOnlyElement(getRelated(TypeDefinitionCategory.RELATIONTYPE_INDEX,Direction.IN),null);
  if (entry == null)   return null;
  assert entry.getSchemaType() instanceof InternalRelationType;
  return (InternalRelationType)entry.getSchemaType();
}
