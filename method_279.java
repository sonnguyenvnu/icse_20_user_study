/** 
 * Get all properties as a string.
 */
public String _XXXXX_(){
  Iterator iterator=getKeys();
  StringBuilder builder=new StringBuilder();
  boolean appendNewline=false;
  while (iterator.hasNext()) {
    Object key=iterator.next();
    if (key instanceof String) {
      if (appendNewline) {
        builder.append("\n");
      }
      Object value=getProperty((String)key);
      builder.append(key).append("=").append(value);
      appendNewline=true;
    }
  }
  return builder.toString();
}