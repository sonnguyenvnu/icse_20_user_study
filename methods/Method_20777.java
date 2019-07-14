/** 
 * Determines if a category is visible given what is the currently expanded category.
 * @param category The category to determine its visibility.
 * @param expandedCategory The category that is currently expandable, possible `null`.
 */
private static boolean isVisible(final @NonNull Category category,final @Nullable Category expandedCategory){
  if (expandedCategory == null) {
    return category.isRoot();
  }
  if (category.isRoot()) {
    return true;
  }
  return category.root().id() == expandedCategory.id();
}
