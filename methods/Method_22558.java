/** 
 * Get translation from bundles. 
 */
static public String text(String key){
  String value=get(key);
  if (value == null) {
    return key;
  }
  return value;
}
