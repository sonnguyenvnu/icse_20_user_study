/** 
 * ?????????????????????
 * @param array
 * @param size
 * @return
 */
private static String[][] resizeArray(String[][] array,int size){
  if (array.length == size)   return array;
  String[][] nArray=new String[size][];
  System.arraycopy(array,0,nArray,0,size);
  return nArray;
}
