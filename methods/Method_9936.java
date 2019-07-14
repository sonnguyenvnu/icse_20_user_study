/** 
 * Filters the specified article tags.
 * @param articleTags the specified article tags
 * @return filtered tags string
 */
public String filterReservedTags(final String articleTags){
  final String[] tags=articleTags.split(",");
  final StringBuilder retBuilder=new StringBuilder();
  for (  final String tag : tags) {
    if (!ArrayUtils.contains(Symphonys.RESERVED_TAGS,tag)) {
      retBuilder.append(tag).append(",");
    }
  }
  if (retBuilder.length() > 0) {
    retBuilder.deleteCharAt(retBuilder.length() - 1);
  }
  return retBuilder.toString();
}
