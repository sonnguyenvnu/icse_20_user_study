/** 
 * Whether the top-level type of  {@code mimeType} is application.
 * @param mimeType The mimeType to test.
 * @return Whether the top level type is application.
 */
public static boolean isApplication(@Nullable String mimeType){
  return BASE_TYPE_APPLICATION.equals(getTopLevelType(mimeType));
}
