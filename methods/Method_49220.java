protected final TypeDefinitionMap makeDefinition(){
  checkGeneralArguments();
  TypeDefinitionMap def=new TypeDefinitionMap();
  def.setValue(INVISIBLE,isInvisible);
  def.setValue(SORT_KEY,checkSortKey(sortKey));
  def.setValue(SORT_ORDER,sortOrder);
  def.setValue(SIGNATURE,checkSignature(signature));
  def.setValue(MULTIPLICITY,multiplicity);
  def.setValue(STATUS,status);
  return def;
}
