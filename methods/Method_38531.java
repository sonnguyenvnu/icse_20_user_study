/** 
 * Makes nice header names.
 */
public static String prepareHeaderParameterName(final String headerName){
  if (headerName.equals("etag")) {
    return HttpBase.HEADER_ETAG;
  }
  if (headerName.equals("www-authenticate")) {
    return "WWW-Authenticate";
  }
  char[] name=headerName.toCharArray();
  boolean capitalize=true;
  for (int i=0; i < name.length; i++) {
    char c=name[i];
    if (c == '-') {
      capitalize=true;
      continue;
    }
    if (capitalize) {
      name[i]=Character.toUpperCase(c);
      capitalize=false;
    }
 else {
      name[i]=Character.toLowerCase(c);
    }
  }
  return new String(name);
}
