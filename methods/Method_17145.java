/** 
 * Configures expire after access. 
 */
void expireAfterAccess(String key,@Nullable String value){
  requireArgument(expireAfterAccessDuration == UNSET_INT,"expireAfterAccess was already set");
  expireAfterAccessDuration=parseDuration(key,value);
  expireAfterAccessTimeUnit=parseTimeUnit(key,value);
}
