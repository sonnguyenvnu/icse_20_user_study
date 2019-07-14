/** 
 * Returns a parsed duration value. 
 */
static long parseDuration(String key,@Nullable String value){
  requireArgument((value != null) && !value.isEmpty(),"value of key %s omitted",key);
  @SuppressWarnings("NullAway") String duration=value.substring(0,value.length() - 1);
  return parseLong(key,duration);
}
