@Nullable public Integer[] resolveIntegerArrayRes(@ArrayRes int resId){
  int[] resIds=resolveIntArrayRes(resId);
  if (resIds == null)   return null;
  Integer[] result=new Integer[resIds.length];
  for (int i=0; i < resIds.length; i++) {
    result[i]=resIds[i];
  }
  return result;
}
