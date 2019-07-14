/** 
 * Validate given text is a valid filename.
 * @param text
 * @return true if given text is a valid filename
 */
public static boolean isValidFilename(String text){
  return (!FILENAME_REGEX.matcher(text).find()) && !".".equals(text) && !"..".equals(text);
}
