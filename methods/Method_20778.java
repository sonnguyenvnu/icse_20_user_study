/** 
 * Since there are two rows that correspond to a root category in an expanded section (e.g. "Art" & "All of Art"), this method will double up that root category in such a situation.
 * @param category The category that might potentially be doubled up.
 * @param expandedCategory The currently expanded category.
 */
private static @NonNull Observable<Category> doubleRootIfExpanded(final @NonNull Category category,final @Nullable Category expandedCategory){
  if (expandedCategory == null) {
    return Observable.just(category);
  }
  if (category.isRoot() && category.id() == expandedCategory.id()) {
    return Observable.just(category,category);
  }
  return Observable.just(category);
}
