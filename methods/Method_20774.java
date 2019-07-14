/** 
 * Given a doubly nested list of all possible category params and an (optional) expanded category this will create a list of sections that can be used in the drawer.
 */
private static @NonNull List<NavigationDrawerData.Section> sectionsFromAllParams(final @NonNull List<List<DiscoveryParams>> sections,final @Nullable Category expandedCategory){
  return Observable.from(sections).map(DiscoveryDrawerUtils::rowsFromParams).map(rows -> Pair.create(rows,rowsAreExpanded(rows,expandedCategory))).map(rowsAndIsExpanded -> NavigationDrawerData.Section.builder().rows(rowsAndIsExpanded.first).expanded(rowsAndIsExpanded.second).build()).toList().toBlocking().single();
}
