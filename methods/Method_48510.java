public static long readInlineRelationType(ReadBuffer in){
  long compressId=VariableLong.readPositive(in);
  return IDManager.addRelationTypePadding(compressId);
}
