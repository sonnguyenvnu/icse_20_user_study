@Override public AsyncFuture<List<URI>> find(){
  final ImmutableList.Builder<AsyncFuture<List<URI>>> lookups=ImmutableList.builder();
  for (  final String record : records) {
    lookups.add(async.call(() -> {
      log.info("Resolving SRV records for: {}",record);
      final Lookup lookup=new Lookup(record,Type.SRV,DClass.IN);
      final Record[] result=lookup.run();
      if (lookup.getResult() != Lookup.SUCCESSFUL) {
        log.error("Failed to lookup record: {}: {}",record,lookup.getErrorString());
        return ImmutableList.<URI>of();
      }
      final ImmutableList.Builder<URI> results=ImmutableList.builder();
      if (result != null) {
        for (        final Record a : result) {
          final SRVRecord srv=(SRVRecord)a;
          results.add(new URI(protocol.orElse(DEFAULT_PROTOCOL) + "://" + srv.getTarget().canonicalize() + ":" + port.orElse(srv.getPort())));
        }
      }
      return results.build();
    }
));
  }
  return async.collect(lookups.build()).directTransform(all -> {
    final ImmutableList.Builder<URI> results=ImmutableList.builder();
    all.forEach(results::addAll);
    return results.build();
  }
);
}
