/** 
 * Configures refresh after write. 
 */
void refreshAfterWrite(String key,@Nullable String value){
  requireArgument(refreshAfterWriteDuration == UNSET_INT,"refreshAfterWrite was already set");
  refreshAfterWriteDuration=parseDuration(key,value);
  refreshAfterWriteTimeUnit=parseTimeUnit(key,value);
}
