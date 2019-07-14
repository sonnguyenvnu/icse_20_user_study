public SliceQuery getQuery(InternalRelationType type,Direction dir,TypedInterval[] sortKey){
  Preconditions.checkNotNull(type);
  Preconditions.checkNotNull(dir);
  Preconditions.checkArgument(type.isUnidirected(Direction.BOTH) || type.isUnidirected(dir));
  StaticBuffer sliceStart=null, sliceEnd=null;
  RelationCategory rt=type.isPropertyKey() ? RelationCategory.PROPERTY : RelationCategory.EDGE;
  if (dir == Direction.BOTH) {
    assert type.isEdgeLabel();
    sliceStart=IDHandler.getRelationType(type.longId(),getDirID(Direction.OUT,rt),type.isInvisibleType());
    sliceEnd=IDHandler.getRelationType(type.longId(),getDirID(Direction.IN,rt),type.isInvisibleType());
    assert sliceStart.compareTo(sliceEnd) < 0;
    sliceEnd=BufferUtil.nextBiggerBuffer(sliceEnd);
  }
 else {
    DirectionID dirID=getDirID(dir,rt);
    DataOutput colStart=serializer.getDataOutput(DEFAULT_COLUMN_CAPACITY);
    DataOutput colEnd=serializer.getDataOutput(DEFAULT_COLUMN_CAPACITY);
    IDHandler.writeRelationType(colStart,type.longId(),dirID,type.isInvisibleType());
    IDHandler.writeRelationType(colEnd,type.longId(),dirID,type.isInvisibleType());
    long[] sortKeyIDs=type.getSortKey();
    Preconditions.checkArgument(sortKey.length >= sortKeyIDs.length);
    assert colStart.getPosition() == colEnd.getPosition();
    int keyStartPos=colStart.getPosition();
    int keyEndPos=-1;
    for (int i=0; i < sortKey.length && sortKey[i] != null; i++) {
      PropertyKey propertyKey=sortKey[i].key;
      Interval interval=sortKey[i].interval;
      if (i >= sortKeyIDs.length) {
        assert !type.multiplicity().isUnique(dir);
        assert (propertyKey instanceof ImplicitKey) && (propertyKey == ImplicitKey.JANUSGRAPHID || propertyKey == ImplicitKey.ADJACENT_ID);
        assert propertyKey != ImplicitKey.ADJACENT_ID || (i == sortKeyIDs.length);
        assert propertyKey != ImplicitKey.JANUSGRAPHID || (!type.multiplicity().isConstrained() && (i == sortKeyIDs.length && propertyKey.isPropertyKey() || i == sortKeyIDs.length + 1 && propertyKey.isEdgeLabel()));
        assert colStart.getPosition() == colEnd.getPosition();
        assert interval == null || interval.isPoints();
        keyEndPos=colStart.getPosition();
      }
 else {
        assert !type.multiplicity().isConstrained();
        assert propertyKey.longId() == sortKeyIDs[i];
      }
      if (interval == null || interval.isEmpty()) {
        break;
      }
      if (interval.isPoints()) {
        if (propertyKey == ImplicitKey.JANUSGRAPHID || propertyKey == ImplicitKey.ADJACENT_ID) {
          assert !type.multiplicity().isUnique(dir);
          VariableLong.writePositiveBackward(colStart,(Long)interval.getStart());
          VariableLong.writePositiveBackward(colEnd,(Long)interval.getEnd());
        }
 else {
          writeInline(colStart,propertyKey,interval.getStart(),InlineType.KEY);
          writeInline(colEnd,propertyKey,interval.getEnd(),InlineType.KEY);
        }
      }
 else {
        if (interval.getStart() != null)         writeInline(colStart,propertyKey,interval.getStart(),InlineType.KEY);
        if (interval.getEnd() != null)         writeInline(colEnd,propertyKey,interval.getEnd(),InlineType.KEY);
switch (type.getSortOrder()) {
case ASC:
          sliceStart=colStart.getStaticBuffer();
        sliceEnd=colEnd.getStaticBuffer();
      if (!interval.startInclusive())       sliceStart=BufferUtil.nextBiggerBuffer(sliceStart);
    if (interval.endInclusive())     sliceEnd=BufferUtil.nextBiggerBuffer(sliceEnd);
  break;
case DESC:
sliceEnd=colStart.getStaticBufferFlipBytes(keyStartPos,colStart.getPosition());
sliceStart=colEnd.getStaticBufferFlipBytes(keyStartPos,colEnd.getPosition());
if (interval.startInclusive()) sliceEnd=BufferUtil.nextBiggerBuffer(sliceEnd);
if (!interval.endInclusive()) sliceStart=BufferUtil.nextBiggerBuffer(sliceStart);
break;
default :
throw new AssertionError(type.getSortOrder().toString());
}
assert sliceStart.compareTo(sliceEnd) <= 0;
break;
}
}
if (sliceStart == null) {
assert sliceEnd == null && colStart.getPosition() == colEnd.getPosition();
if (keyEndPos < 0) keyEndPos=colStart.getPosition();
switch (type.getSortOrder()) {
case ASC:
sliceStart=colStart.getStaticBuffer();
break;
case DESC:
sliceStart=colStart.getStaticBufferFlipBytes(keyStartPos,keyEndPos);
break;
default :
throw new AssertionError(type.getSortOrder().toString());
}
sliceEnd=BufferUtil.nextBiggerBuffer(sliceStart);
}
}
return new SliceQuery(sliceStart,sliceEnd);
}
