private Single<Result<Parsed>> fetchAndPersistResult(@Nonnull final Key key){
  try {
    return inFlightRequests.get(key,() -> responseResult(key));
  }
 catch (  ExecutionException e) {
    return Single.error(e);
  }
}
