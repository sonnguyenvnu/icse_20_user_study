/** 
 * @return data from memory
 */
private Observable<Parsed> lazyCache(@Nonnull final Key key){
  return Observable.defer(() -> cache(key)).onErrorResumeNext(Observable.empty());
}
