/** 
 * Returns the extended sort key of the given type. The extended sort key extends the type's primary sort key by ADJACENT_ID and ID depending on the multiplicity of the type in the given direction. It also converts the type ids to actual types.
 * @param type
 * @param dir
 * @param tx
 * @return
 */
private static PropertyKey[] getExtendedSortKey(InternalRelationType type,Direction dir,StandardJanusGraphTx tx){
  int additional=0;
  if (!type.multiplicity().isUnique(dir)) {
    if (!type.multiplicity().isConstrained())     additional++;
    if (type.isEdgeLabel())     additional++;
  }
  PropertyKey[] entireKey=new PropertyKey[type.getSortKey().length + additional];
  int i;
  for (i=0; i < type.getSortKey().length; i++) {
    entireKey[i]=tx.getExistingPropertyKey(type.getSortKey()[i]);
  }
  if (type.isEdgeLabel() && !type.multiplicity().isUnique(dir))   entireKey[i++]=ImplicitKey.ADJACENT_ID;
  if (!type.multiplicity().isConstrained())   entireKey[i]=ImplicitKey.JANUSGRAPHID;
  return entireKey;
}
