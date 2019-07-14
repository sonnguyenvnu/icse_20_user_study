public static int relationTypeLength(long relationTypeId){
  assert relationTypeId > 0 && (relationTypeId << 1) > 0;
  return VariableLong.positiveWithPrefixLength(IDManager.stripEntireRelationTypePadding(relationTypeId) << 1,PREFIX_BIT_LEN);
}
