public StaticArrayEntry writeRelation(InternalRelation relation,InternalRelationType type,int position,TypeInspector tx){
  assert type == relation.getType() || (type.getBaseType() != null && type.getBaseType().equals(relation.getType()));
  Direction dir=EdgeDirection.fromPosition(position);
  Preconditions.checkArgument(type.isUnidirected(Direction.BOTH) || type.isUnidirected(dir));
  long typeId=type.longId();
  DirectionID dirID=getDirID(dir,relation.isProperty() ? RelationCategory.PROPERTY : RelationCategory.EDGE);
  DataOutput out=serializer.getDataOutput(DEFAULT_CAPACITY);
  int valuePosition;
  IDHandler.writeRelationType(out,typeId,dirID,type.isInvisibleType());
  Multiplicity multiplicity=type.multiplicity();
  long[] sortKey=type.getSortKey();
  assert !multiplicity.isConstrained() || sortKey.length == 0 : type.name();
  int keyStartPos=out.getPosition();
  if (!multiplicity.isConstrained()) {
    writeInlineTypes(sortKey,relation,out,tx,InlineType.KEY);
  }
  int keyEndPos=out.getPosition();
  long relationId=relation.longId();
  if (relation.isEdge()) {
    long otherVertexId=relation.getVertex((position + 1) % 2).longId();
    if (multiplicity.isConstrained()) {
      if (multiplicity.isUnique(dir)) {
        valuePosition=out.getPosition();
        VariableLong.writePositive(out,otherVertexId);
      }
 else {
        VariableLong.writePositiveBackward(out,otherVertexId);
        valuePosition=out.getPosition();
      }
      VariableLong.writePositive(out,relationId);
    }
 else {
      VariableLong.writePositiveBackward(out,otherVertexId);
      VariableLong.writePositiveBackward(out,relationId);
      valuePosition=out.getPosition();
    }
  }
 else {
    assert relation.isProperty();
    Preconditions.checkArgument(relation.isProperty());
    Object value=((JanusGraphVertexProperty)relation).value();
    Preconditions.checkNotNull(value);
    PropertyKey key=(PropertyKey)type;
    assert key.dataType().isInstance(value);
    if (multiplicity.isConstrained()) {
      if (multiplicity.isUnique(dir)) {
        valuePosition=out.getPosition();
        writePropertyValue(out,key,value);
      }
 else {
        writePropertyValue(out,key,value);
        valuePosition=out.getPosition();
      }
      VariableLong.writePositive(out,relationId);
    }
 else {
      assert multiplicity.getCardinality() == Cardinality.LIST;
      VariableLong.writePositiveBackward(out,relationId);
      valuePosition=out.getPosition();
      writePropertyValue(out,key,value);
    }
  }
  long[] signature=type.getSignature();
  writeInlineTypes(signature,relation,out,tx,InlineType.SIGNATURE);
  LongSet writtenTypes=new LongHashSet(sortKey.length + signature.length);
  if (sortKey.length > 0 || signature.length > 0) {
    for (    long id : sortKey)     writtenTypes.add(id);
    for (    long id : signature)     writtenTypes.add(id);
  }
  LongArrayList remainingTypes=new LongArrayList(8);
  for (  PropertyKey t : relation.getPropertyKeysDirect()) {
    if (!(t instanceof ImplicitKey) && !writtenTypes.contains(t.longId())) {
      remainingTypes.add(t.longId());
    }
  }
  long[] remaining=remainingTypes.toArray();
  Arrays.sort(remaining);
  for (  long tid : remaining) {
    PropertyKey t=tx.getExistingPropertyKey(tid);
    writeInline(out,t,relation.getValueDirect(t),InlineType.NORMAL);
  }
  assert valuePosition > 0;
  return new StaticArrayEntry(type.getSortOrder() == Order.DESC ? out.getStaticBufferFlipBytes(keyStartPos,keyEndPos) : out.getStaticBuffer(),valuePosition);
}
