/** 
 * Converts type reference to java-name.
 */
public static String typeref2Name(final String desc){
  if (desc.charAt(0) != TYPE_REFERENCE) {
    throw new IllegalArgumentException(INVALID_TYPE_DESCRIPTION + desc);
  }
  String name=desc.substring(1,desc.length() - 1);
  return name.replace('/','.');
}
