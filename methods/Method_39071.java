/** 
 * Returns resolved alias result value or passed on, if alias doesn't exist.
 */
protected String resolveAlias(final String value){
  final StringBuilder result=new StringBuilder(value.length());
  int i=0;
  int len=value.length();
  while (i < len) {
    int ndx=value.indexOf('<',i);
    if (ndx == -1) {
      if (i == 0) {
        String alias=lookupAlias(value);
        return (alias != null ? alias : value);
      }
 else {
        result.append(value.substring(i));
      }
      break;
    }
    result.append(value.substring(i,ndx));
    ndx++;
    int ndx2=value.indexOf('>',ndx);
    String aliasName=(ndx2 == -1 ? value.substring(ndx) : value.substring(ndx,ndx2));
    String alias=lookupAlias(aliasName);
    if (alias != null) {
      result.append(alias);
    }
 else {
      if (log.isWarnEnabled()) {
        log.warn("Alias not found: " + aliasName);
      }
    }
    i=ndx2 + 1;
  }
  i=0;
  len=result.length();
  while (i < len) {
    if (result.charAt(i) != '/') {
      break;
    }
    i++;
  }
  if (i > 1) {
    return result.substring(i - 1,len);
  }
  return result.toString();
}
