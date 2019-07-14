/** 
 * Returns a parsed long value. 
 */
static long parseLong(String key,@Nullable String value){
  requireArgument((value != null) && !value.isEmpty(),"value of key %s was omitted",key);
  try {
    return Long.parseLong(value);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException(String.format("key %s value was set to %s, must be a long",key,value),e);
  }
}
