/** 
 * Creates unique key for method signatures map.
 */
public static String createMethodSignaturesKey(final int access,final String methodName,final String description,final String className){
  return new StringBand(7).append(access).append(COLON).append(description).append(StringPool.UNDERSCORE).append(className).append(StringPool.HASH).append(methodName).toString();
}
