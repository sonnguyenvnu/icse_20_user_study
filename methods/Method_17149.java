/** 
 * Returns a parsed int value. 
 */
static int parseInt(String key,@Nullable String value){
  requireArgument((value != null) && !value.isEmpty(),"value of key %s was omitted",key);
  try {
    return Integer.parseInt(value);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException(String.format("key %s value was set to %s, must be an integer",key,value),e);
  }
}
