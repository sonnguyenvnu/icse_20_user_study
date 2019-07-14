@Nullable public Integer[] resolveIntegerArrayAttr(@AttrRes int attrResId,@ArrayRes int defResId){
  int[] resIds=resolveIntArrayAttr(attrResId,defResId);
  if (resIds == null)   return null;
  Integer[] result=new Integer[resIds.length];
  for (int i=0; i < resIds.length; i++) {
    result[i]=resIds[i];
  }
  return result;
}
