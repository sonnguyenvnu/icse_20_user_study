/** 
 * ????
 * @param tag
 * @return
 */
public static String translate(String tag){
  String cn=translator.get(tag);
  if (cn == null)   return tag;
  return cn;
}
