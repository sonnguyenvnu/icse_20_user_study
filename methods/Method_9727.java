/** 
 * Checks the specified tag string whether contains the white list tags.
 * @param tagStr the specified tag string
 * @return {@code true} if it contains, returns {@code false} otherwise
 */
public static boolean containsWhiteListTags(final String tagStr){
  for (  final String whiteListTag : Symphonys.WHITE_LIST_TAGS) {
    if (StringUtils.equalsIgnoreCase(tagStr,whiteListTag)) {
      return true;
    }
  }
  return false;
}
