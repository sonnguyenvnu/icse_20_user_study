/** 
 * Creates a Change of type DELETE_RANGE. As a result of this Change  {@param count} items startingat  {@param index} in the context of the {@link DiffSectionSpec} creating this Change will beremoved.
 */
static Change removeRange(int index,int count,@Nullable List<?> prevData){
  return acquireRangedChange(DELETE_RANGE,index,count,EMPTY,prevData,null);
}
