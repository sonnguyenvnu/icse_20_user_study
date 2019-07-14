/** 
 * Only update memory after persister has been successfully updated
 * @param key
 * @param data
 */
void updateMemory(@Nonnull final Key key,final Parsed data){
  memCache.put(key,Observable.just(data));
}
