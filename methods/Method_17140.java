/** 
 * Parses and applies the configuration option. 
 */
void parseOption(String option){
  if (option.isEmpty()) {
    return;
  }
  @SuppressWarnings("StringSplitter") String[] keyAndValue=option.split(SPLIT_KEY_VALUE);
  requireArgument(keyAndValue.length <= 2,"key-value pair %s with more than one equals sign",option);
  String key=keyAndValue[0].trim();
  String value=(keyAndValue.length == 1) ? null : keyAndValue[1].trim();
  configure(key,value);
}
