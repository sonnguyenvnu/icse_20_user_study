public static RelationTypeParse readRelationType(ReadBuffer in){
  long[] countPrefix=VariableLong.readPositiveWithPrefix(in,PREFIX_BIT_LEN);
  DirectionID dirID=DirectionID.getDirectionID((int)countPrefix[1] & 1,(int)(countPrefix[0] & 1));
  long typeId=countPrefix[0] >>> 1;
  boolean isSystemType=(countPrefix[1] >> 1) == 0;
  if (dirID == DirectionID.PROPERTY_DIR)   typeId=IDManager.getSchemaId(isSystemType ? SystemPropertyKey : UserPropertyKey,typeId);
 else   typeId=IDManager.getSchemaId(isSystemType ? SystemEdgeLabel : UserEdgeLabel,typeId);
  return new RelationTypeParse(typeId,dirID);
}
