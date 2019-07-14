/** 
 * Checks the specified tag string whether contains the reserved tags.
 * @param tagStr the specified tag string
 * @return {@code true} if it contains, returns {@code false} otherwise
 */
public static boolean containsReservedTags(final String tagStr){
  for (  final String reservedTag : Symphonys.RESERVED_TAGS) {
    if (StringUtils.containsIgnoreCase(tagStr,reservedTag)) {
      return true;
    }
  }
  return false;
}
