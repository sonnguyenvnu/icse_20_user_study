/** 
 * Extracts header parameter. Returns <code>null</code> if parameter not found.
 */
public static String extractHeaderParameter(final String header,final String parameter,final char separator){
  int index=0;
  while (true) {
    index=header.indexOf(separator,index);
    if (index == -1) {
      return null;
    }
    index++;
    while (index < header.length() && header.charAt(index) == ' ') {
      index++;
    }
    int eqNdx=header.indexOf('=',index);
    if (eqNdx == -1) {
      return null;
    }
    String paramName=header.substring(index,eqNdx);
    eqNdx++;
    if (!paramName.equalsIgnoreCase(parameter)) {
      index=eqNdx;
      continue;
    }
    int endIndex=header.indexOf(';',eqNdx);
    if (endIndex == -1) {
      return header.substring(eqNdx);
    }
 else {
      return header.substring(eqNdx,endIndex);
    }
  }
}
