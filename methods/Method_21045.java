/** 
 * Determines if the `include_featured` flag should be included in a discovery request so that we guarantee that the featured project for the category comes back.
 */
public boolean shouldIncludeFeatured(){
  return category() != null && category().parent() == null && page() != null && page() == 1 && (sort() == null || sort() == Sort.HOME);
}
