/** 
 * Fetch data from persister and update memory after. If an error occurs, emit an empty observable so that the concat call in  {@link #get(Key)} moves on to {@link #fetch(Key)}
 * @param key
 * @return
 */
@Nonnull public Observable<Parsed> disk(@Nonnull final Key key){
  if (StoreUtil.shouldReturnNetworkBeforeStale(persister,stalePolicy,key)) {
    return Observable.empty();
  }
  return readDisk(key);
}
