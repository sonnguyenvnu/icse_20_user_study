/** 
 * Converts table name to entity (type) name.
 */
public String convertTableNameToEntityName(final String tableName){
  StringBuilder className=new StringBuilder(tableName.length());
  int len=tableName.length();
  int i=0;
  if (prefix != null) {
    if (tableName.startsWith(prefix)) {
      i=prefix.length();
    }
  }
  if (suffix != null) {
    if (tableName.endsWith(suffix)) {
      len-=suffix.length();
    }
  }
  if (splitCamelCase) {
    boolean toUpper=true;
    for (; i < len; i++) {
      char c=tableName.charAt(i);
      if (c == separatorChar) {
        toUpper=true;
        continue;
      }
      if (toUpper) {
        className.append(Character.toUpperCase(c));
        toUpper=false;
      }
 else {
        className.append(Character.toLowerCase(c));
      }
    }
    return className.toString();
  }
  return tableName.substring(i,len);
}
