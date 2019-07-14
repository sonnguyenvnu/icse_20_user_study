/** 
 * Creates observables that will perform API requests to retrieve additional data needed to fill out a full discovery params object. For example, if `params` holds only a category slug and no actual category data, we will perform a request to get the full category from the API.
 * @param params The discovery params that is potentially missing full data.
 * @return A list of observables, each responsible for retrieving more data from the API. Theobservables emit *builders* of params, and hence can later be merged into a single params object.
 */
private static @NonNull List<Observable<DiscoveryParams.Builder>> paramBuilders(final @NonNull DiscoveryParams params,final @NonNull ApiClientType client){
  final List<Observable<DiscoveryParams.Builder>> paramBuilders=new ArrayList<>();
  final String categoryParam=params.categoryParam();
  if (categoryParam != null) {
    paramBuilders.add(client.fetchCategory(categoryParam).map(c -> DiscoveryParams.builder().category(c)).compose(Transformers.neverError()));
  }
  final String locationParam=params.locationParam();
  if (locationParam != null) {
    paramBuilders.add(client.fetchLocation(locationParam).map(l -> DiscoveryParams.builder().location(l)).compose(Transformers.neverError()));
  }
  paramBuilders.add(Observable.just(params.toBuilder()));
  return paramBuilders;
}
