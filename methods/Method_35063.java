/** 
 * @return data from memory
 */
private Maybe<Parsed> lazyCache(@Nonnull final Key key){
  return Maybe.defer(() -> cache(key)).onErrorResumeNext(Maybe.<Parsed>empty());
}
