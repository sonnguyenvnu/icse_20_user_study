/** 
 * Concatenate the given  {@code String} arrays into one,with overlapping array elements included twice. <p>The order of elements in the original arrays is preserved.
 * @param array1 the first array (can be {@code null})
 * @param array2 the second array (can be {@code null})
 * @return the new array ({@code null} if both given arrays were {@code null})
 */
public static String[] concatenateStringArrays(String[] array1,String[] array2){
  if (ObjectUtils.isEmpty(array1)) {
    return array2;
  }
  if (ObjectUtils.isEmpty(array2)) {
    return array1;
  }
  String[] newArr=new String[array1.length + array2.length];
  System.arraycopy(array1,0,newArr,0,array1.length);
  System.arraycopy(array2,0,newArr,array1.length,array2.length);
  return newArr;
}
