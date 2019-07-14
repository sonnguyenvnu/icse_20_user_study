/** 
 * Configures expire after write. 
 */
void expireAfterWrite(String key,@Nullable String value){
  requireArgument(expireAfterWriteDuration == UNSET_INT,"expireAfterWrite was already set");
  expireAfterWriteDuration=parseDuration(key,value);
  expireAfterWriteTimeUnit=parseTimeUnit(key,value);
}
