/** 
 * Set the tags. It will remove all previous tags first.
 * @param tags the tag list to set.
 */
public void setTags(String... tags){
  removeAllViews();
  for (  final String tag : tags) {
    appendTag(tag);
  }
  if (isAppendMode) {
    appendInputTag();
  }
}
