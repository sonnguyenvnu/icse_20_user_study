private RelationTypeIndex buildRelationTypeIndex(RelationType type,String name,Direction direction,Order sortOrder,PropertyKey... sortKeys){
  Preconditions.checkArgument(type != null && direction != null && sortOrder != null && sortKeys != null);
  Preconditions.checkArgument(StringUtils.isNotBlank(name),"Name cannot be blank: %s",name);
  Token.verifyName(name);
  Preconditions.checkArgument(sortKeys.length > 0,"Need to specify sort keys");
  for (  RelationType key : sortKeys)   Preconditions.checkArgument(key != null,"Keys cannot be null");
  Preconditions.checkArgument(!(type instanceof EdgeLabel) || !((EdgeLabel)type).isUnidirected() || direction == Direction.OUT,"Can only index uni-directed labels in the out-direction: %s",type);
  Preconditions.checkArgument(!((InternalRelationType)type).multiplicity().isUnique(direction),"The relation type [%s] has a multiplicity or cardinality constraint in direction [%s] and can therefore not be indexed",type,direction);
  String composedName=composeRelationTypeIndexName(type,name);
  StandardRelationTypeMaker maker;
  if (type.isEdgeLabel()) {
    StandardEdgeLabelMaker lm=(StandardEdgeLabelMaker)transaction.makeEdgeLabel(composedName);
    lm.unidirected(direction);
    maker=lm;
  }
 else {
    assert type.isPropertyKey();
    assert direction == Direction.OUT;
    StandardPropertyKeyMaker lm=(StandardPropertyKeyMaker)transaction.makePropertyKey(composedName);
    lm.dataType(((PropertyKey)type).dataType());
    maker=lm;
  }
  maker.status(type.isNew() ? SchemaStatus.ENABLED : SchemaStatus.INSTALLED);
  maker.invisible();
  maker.multiplicity(Multiplicity.MULTI);
  maker.sortKey(sortKeys);
  maker.sortOrder(sortOrder);
  long[] typeSig=((InternalRelationType)type).getSignature();
  Set<PropertyKey> signature=Sets.newHashSet();
  for (  long typeId : typeSig)   signature.add(transaction.getExistingPropertyKey(typeId));
  for (  RelationType sortType : sortKeys)   signature.remove(sortType);
  if (!signature.isEmpty()) {
    PropertyKey[] sig=signature.toArray(new PropertyKey[signature.size()]);
    maker.signature(sig);
  }
  RelationType typeIndex=maker.make();
  addSchemaEdge(type,typeIndex,TypeDefinitionCategory.RELATIONTYPE_INDEX,null);
  RelationTypeIndexWrapper index=new RelationTypeIndexWrapper((InternalRelationType)typeIndex);
  if (!type.isNew())   updateIndex(index,SchemaAction.REGISTER_INDEX);
  return index;
}
