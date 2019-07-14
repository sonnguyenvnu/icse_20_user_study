/** 
 * Crops string by setting empty strings to <code>null</code>.
 */
public static String crop(final String string){
  if (string.length() == 0) {
    return null;
  }
  return string;
}
