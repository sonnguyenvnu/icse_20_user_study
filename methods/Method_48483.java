@Override public RelationCache parseRelation(Entry data,boolean excludeProperties,TypeInspector tx){
  ReadBuffer in=data.asReadBuffer();
  LongObjectHashMap properties=excludeProperties ? null : new LongObjectHashMap(4);
  RelationTypeParse typeAndDir=IDHandler.readRelationType(in);
  long typeId=typeAndDir.typeId;
  Direction dir=typeAndDir.dirID.getDirection();
  RelationType relationType=tx.getExistingRelationType(typeId);
  InternalRelationType def=(InternalRelationType)relationType;
  Multiplicity multiplicity=def.multiplicity();
  long[] keySignature=def.getSortKey();
  long relationId;
  Object other;
  int startKeyPos=in.getPosition();
  int endKeyPos=0;
  if (relationType.isEdgeLabel()) {
    long otherVertexId;
    if (multiplicity.isConstrained()) {
      if (multiplicity.isUnique(dir)) {
        otherVertexId=VariableLong.readPositive(in);
      }
 else {
        in.movePositionTo(data.getValuePosition());
        otherVertexId=VariableLong.readPositiveBackward(in);
        in.movePositionTo(data.getValuePosition());
      }
      relationId=VariableLong.readPositive(in);
    }
 else {
      in.movePositionTo(data.getValuePosition());
      relationId=VariableLong.readPositiveBackward(in);
      otherVertexId=VariableLong.readPositiveBackward(in);
      endKeyPos=in.getPosition();
      in.movePositionTo(data.getValuePosition());
    }
    other=otherVertexId;
  }
 else {
    assert relationType.isPropertyKey();
    PropertyKey key=(PropertyKey)relationType;
    if (multiplicity.isConstrained()) {
      other=readPropertyValue(in,key);
      relationId=VariableLong.readPositive(in);
    }
 else {
      in.movePositionTo(data.getValuePosition());
      relationId=VariableLong.readPositiveBackward(in);
      endKeyPos=in.getPosition();
      in.movePositionTo(data.getValuePosition());
      other=readPropertyValue(in,key);
    }
    Preconditions.checkNotNull(other,"Encountered error in deserializer [null value returned]. Check serializer compatibility.");
  }
  assert other != null;
  if (!excludeProperties && !multiplicity.isConstrained() && keySignature.length > 0) {
    int currentPos=in.getPosition();
    assert endKeyPos > startKeyPos;
    int keyLength=endKeyPos - startKeyPos;
    in.movePositionTo(startKeyPos);
    ReadBuffer inKey=in;
    if (def.getSortOrder() == Order.DESC)     inKey=in.subrange(keyLength,true);
    readInlineTypes(keySignature,properties,inKey,tx,InlineType.KEY);
    in.movePositionTo(currentPos);
  }
  if (!excludeProperties) {
    readInlineTypes(def.getSignature(),properties,in,tx,InlineType.SIGNATURE);
    while (in.hasRemaining()) {
      PropertyKey type=tx.getExistingPropertyKey(IDHandler.readInlineRelationType(in));
      Object propertyValue=readInline(in,type,InlineType.NORMAL);
      assert propertyValue != null;
      properties.put(type.longId(),propertyValue);
    }
    if (data.hasMetaData()) {
      for (      Map.Entry<EntryMetaData,Object> metas : data.getMetaData().entrySet()) {
        ImplicitKey key=ImplicitKey.MetaData2ImplicitKey.get(metas.getKey());
        if (key != null) {
          assert metas.getValue() != null;
          properties.put(key.longId(),metas.getValue());
        }
      }
    }
  }
  return new RelationCache(dir,typeId,relationId,other,properties);
}
