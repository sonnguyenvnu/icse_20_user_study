/** 
 * Append arg to map
 * @param key
 * @param value
 */
public void appendArg(String key,String value){
  if (args != null) {
    args.put(key,value);
  }
}
