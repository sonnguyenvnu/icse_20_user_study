/** 
 * From a list of rows and the currently expanded category figures out if the rows are expanded.
 */
private static boolean rowsAreExpanded(final List<NavigationDrawerData.Section.Row> rows,final @Nullable Category expandedCategory){
  final Category sectionCategory=rows.get(0).params().category();
  return sectionCategory != null && expandedCategory != null && sectionCategory.rootId() == expandedCategory.rootId();
}
