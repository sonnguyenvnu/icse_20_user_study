/** 
 * @return new pointer value
 */
protected int delete(int pointer){
  assert !isEmpty(pointer);
  int n=indexes.length;
  int[] newIndexes=new int[n - 1];
  Object[] newValues=new Object[n - 1];
  System.arraycopy(indexes,0,newIndexes,0,pointer);
  System.arraycopy(values,0,newValues,0,pointer);
  System.arraycopy(indexes,pointer + 1,newIndexes,pointer,n - pointer - 1);
  System.arraycopy(values,pointer + 1,newValues,pointer,n - pointer - 1);
  indexes=newIndexes;
  values=newValues;
  pointer=-pointer - 2;
  return pointer;
}
