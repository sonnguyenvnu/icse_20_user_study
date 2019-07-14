/** 
 * Whether the top-level type of  {@code mimeType} is audio.
 * @param mimeType The mimeType to test.
 * @return Whether the top level type is audio.
 */
public static boolean isAudio(@Nullable String mimeType){
  return BASE_TYPE_AUDIO.equals(getTopLevelType(mimeType));
}
