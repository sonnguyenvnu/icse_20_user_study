/** 
 * Determines whether the specified tag title is reserved.
 * @param tagTitle the specified tag title
 * @return {@code true} if it is reserved, otherwise returns {@code false}
 */
public boolean isReservedTag(final String tagTitle){
  return ArrayUtils.contains(Symphonys.RESERVED_TAGS,tagTitle);
}
