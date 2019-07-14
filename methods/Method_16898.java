/** 
 * Returns an encoded form of the class name for compact use. 
 */
private static String encode(String className){
  return Feature.makeEnumName(className).replaceFirst("STRONG_KEYS","S").replaceFirst("WEAK_KEYS","W").replaceFirst("_STRONG_VALUES","S").replaceFirst("_INFIRM_VALUES","I").replaceFirst("_LISTENING","L").replaceFirst("_STATS","S").replaceFirst("_MAXIMUM","M").replaceFirst("_WEIGHT","W").replaceFirst("_SIZE","S").replaceFirst("_EXPIRE_ACCESS","A").replaceFirst("_EXPIRE_WRITE","W").replaceFirst("_REFRESH_WRITE","R");
}
