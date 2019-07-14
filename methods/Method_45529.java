/** 
 * ??????????"false"?"null"
 * @param string ???
 * @return ?????"false"?"null"
 */
protected static boolean assertFalse(String string){
  return string == null || StringUtils.EMPTY.equals(string) || StringUtils.FALSE.equalsIgnoreCase(string) || StringUtils.NULL.equals(string);
}
