/** 
 * @return data from memory
 */
private Maybe<Result<Parsed>> lazyCacheWithResult(@Nonnull final Key key){
  return Maybe.defer(() -> cacheWithResult(key)).onErrorResumeNext(Maybe.<Result<Parsed>>empty());
}
