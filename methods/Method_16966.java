/** 
 * Returns an encoded form of the class name for compact use. 
 */
private static String encode(String className){
  return Feature.makeEnumName(className).replaceFirst("STRONG_KEYS","P").replaceFirst("WEAK_KEYS","F").replaceFirst("_STRONG_VALUES","S").replaceFirst("_WEAK_VALUES","W").replaceFirst("_SOFT_VALUES","D").replaceFirst("_EXPIRE_ACCESS","A").replaceFirst("_EXPIRE_WRITE","W").replaceFirst("_REFRESH_WRITE","R").replaceFirst("_MAXIMUM","M").replaceFirst("_WEIGHT","W").replaceFirst("_SIZE","S");
}
