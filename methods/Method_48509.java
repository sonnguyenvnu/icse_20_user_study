public static void writeInlineRelationType(WriteBuffer out,long relationTypeId){
  long compressId=IDManager.stripRelationTypePadding(relationTypeId);
  VariableLong.writePositive(out,compressId);
}
