/** 
 * ????????? ???true
 * @param str ???
 * @return
 */
public static boolean isNullString(@Nullable String str){
  return str == null || str.length() == 0 || "null".equals(str);
}
