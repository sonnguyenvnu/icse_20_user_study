/** 
 * Returns params where category and location params have been converted into  {@link Category}and  {@link Location} objects.
 */
private static @NonNull Observable<DiscoveryParams> paramsFromUri(final @NonNull DiscoveryParams params,final @NonNull ApiClientType client){
  return Observable.zip(paramBuilders(params,client),builders -> {
    DiscoveryParams.Builder builder=DiscoveryParams.builder();
    for (    final Object object : builders) {
      final DiscoveryParams.Builder b=(DiscoveryParams.Builder)object;
      builder=builder.mergeWith(b);
    }
    return builder.build();
  }
);
}
