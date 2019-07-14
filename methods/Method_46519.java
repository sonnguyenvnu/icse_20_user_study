/** 
 * ????????????
 * @param suffix        suffix
 * @param stringBuilder stringBuilder
 */
public static void cutSuffix(String suffix,StringBuilder stringBuilder){
  if (stringBuilder.substring(stringBuilder.length() - suffix.length()).equals(suffix)) {
    stringBuilder.delete(stringBuilder.length() - suffix.length(),stringBuilder.length());
  }
}
