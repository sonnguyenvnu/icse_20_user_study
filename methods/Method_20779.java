/** 
 * Returns a list of top-level section filters that can be used based on the current user, which could be `null`. Each filter is its own section containing one single row.
 * @param user The currently logged in user, can be `null`.
 */
private static @NonNull List<NavigationDrawerData.Section> topSections(final @Nullable User user){
  final List<DiscoveryParams> filters=ListUtils.empty();
  final boolean userIsLoggedIn=user != null;
  if (userIsLoggedIn && isFalse(user.optedOutOfRecommendations())) {
    filters.add(DiscoveryParams.builder().recommended(true).backed(-1).build());
  }
  filters.add(DiscoveryParams.builder().build());
  filters.add(DiscoveryParams.builder().staffPicks(true).build());
  if (userIsLoggedIn) {
    filters.add(DiscoveryParams.builder().starred(1).build());
    if (isTrue(user.social())) {
      filters.add(DiscoveryParams.builder().social(1).build());
    }
  }
  return Observable.from(filters).map(p -> NavigationDrawerData.Section.Row.builder().params(p).build()).map(Collections::singletonList).map(rows -> NavigationDrawerData.Section.builder().rows(rows).build()).toList().toBlocking().single();
}
