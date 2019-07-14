/** 
 * Gets a tag URI with the specified tag title.
 * @param title the specified tag title
 * @return tag URI, returns {@code null} if not found
 */
public String getURIByTitle(final String title){
  return tagCache.getURIByTitle(title);
}
