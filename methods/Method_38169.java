/** 
 * Returns the query string.
 */
public String getQueryString(){
  if (sqlTemplate == null) {
    return toString();
  }
  if (parameterValues == null) {
    return sqlTemplate;
  }
  final StringBuilder sb=new StringBuilder();
  int qMarkCount=0;
  final StringTokenizer tok=new StringTokenizer(sqlTemplate + ' ',"?");
  while (tok.hasMoreTokens()) {
    final String oneChunk=tok.nextToken();
    sb.append(oneChunk);
    try {
      Object value=null;
      if (parameterValues.size() > 1 + qMarkCount) {
        value=parameterValues.get(1 + qMarkCount);
        qMarkCount++;
      }
 else {
        if (!tok.hasMoreTokens()) {
          value="";
        }
      }
      if (value == null) {
        value="?";
      }
      sb.append(value);
    }
 catch (    Throwable th) {
      sb.append("--- Building query failed: ").append(th.toString());
    }
  }
  return sb.toString().trim();
}
