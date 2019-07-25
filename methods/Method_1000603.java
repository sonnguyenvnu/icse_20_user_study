/** 
 * ???????????? Map ???
 * @param str ?? JSON ???????????????????
 * @return Map ??
 */
public static NutMap map(String str){
  if (null == str)   return null;
  str=Strings.trim(str);
  if (!Strings.isEmpty(str) && (Strings.isQuoteBy(str,'{','}') || Strings.isQuoteBy(str,'(',')'))) {
    return Json.fromJson(NutMap.class,str);
  }
  return Json.fromJson(NutMap.class,"{" + str + "}");
}
