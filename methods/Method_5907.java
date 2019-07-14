/** 
 * Whether the top-level type of  {@code mimeType} is text.
 * @param mimeType The mimeType to test.
 * @return Whether the top level type is text.
 */
public static boolean isText(@Nullable String mimeType){
  return BASE_TYPE_TEXT.equals(getTopLevelType(mimeType));
}
