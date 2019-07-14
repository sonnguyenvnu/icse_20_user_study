/** 
 * Converts a list of params into a list of rows that the drawer can use to display rows.
 */
private static @NonNull List<NavigationDrawerData.Section.Row> rowsFromParams(final @NonNull List<DiscoveryParams> params){
  return Observable.from(params).map(p -> NavigationDrawerData.Section.Row.builder().params(p).build()).toList().toBlocking().single();
}
