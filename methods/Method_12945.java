/** 
 * Remove duplicate strings from the given array. <p>As of 4.2, it preserves the original order, as it uses a  {@link LinkedHashSet}.
 * @param array the {@code String} array
 * @return an array without duplicates, in natural sort order
 */
public static String[] removeDuplicateStrings(String[] array){
  if (ObjectUtils.isEmpty(array)) {
    return array;
  }
  Set<String> set=new LinkedHashSet<String>();
  for (  String element : array) {
    set.add(element);
  }
  return toStringArray(set);
}
