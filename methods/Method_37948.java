/** 
 * Creates new Properties object from the original one, by copying those properties that have specified first part of the key name. Prefix may be optionally stripped during this process.
 * @param p         source properties, from which new object will be created
 * @param prefix    key names prefix 
 * @return subset properties
 */
public static Properties subset(final Properties p,String prefix,final boolean stripPrefix){
  if (StringUtil.isBlank(prefix)) {
    return p;
  }
  if (!prefix.endsWith(StringPool.DOT)) {
    prefix+='.';
  }
  Properties result=new Properties();
  int baseLen=prefix.length();
  for (  Object o : p.keySet()) {
    String key=(String)o;
    if (key.startsWith(prefix)) {
      result.setProperty(stripPrefix ? key.substring(baseLen) : key,p.getProperty(key));
    }
  }
  return result;
}
