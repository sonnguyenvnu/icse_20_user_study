/** 
 * Will check to see if there exists an in flight observable and return it before going to network
 * @return data from fetch and store it in memory and persister
 */
@Nonnull @Override public Observable<Parsed> fetch(@Nonnull final Key key){
  return Observable.defer(() -> fetchAndPersist(key));
}
