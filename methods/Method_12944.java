/** 
 * Merge the given  {@code String} arrays into one, with overlappingarray elements only included once. <p>The order of elements in the original arrays is preserved (with the exception of overlapping elements, which are only included on their first occurrence).
 * @param array1 the first array (can be {@code null})
 * @param array2 the second array (can be {@code null})
 * @return the new array ({@code null} if both given arrays were {@code null})
 */
public static String[] mergeStringArrays(String[] array1,String[] array2){
  if (ObjectUtils.isEmpty(array1)) {
    return array2;
  }
  if (ObjectUtils.isEmpty(array2)) {
    return array1;
  }
  List<String> result=new ArrayList<String>();
  result.addAll(Arrays.asList(array1));
  for (  String str : array2) {
    if (!result.contains(str)) {
      result.add(str);
    }
  }
  return toStringArray(result);
}
