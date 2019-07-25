/** 
 * Helper to format a human readable error message for this exception. 
 */
private static String msg(String errorMessage,String logMessage,int errorStart,int errorEnd){
  if (errorEnd < 0) {
    errorEnd=logMessage.length();
  }
  StringBuilder out=new StringBuilder(errorMessage).append(": ");
  if (errorStart > SNIPPET_LENGTH + ELLIPSIS.length()) {
    out.append(ELLIPSIS).append(logMessage,errorStart - SNIPPET_LENGTH,errorStart);
  }
 else {
    out.append(logMessage,0,errorStart);
  }
  out.append('[').append(logMessage.substring(errorStart,errorEnd)).append(']');
  if (logMessage.length() - errorEnd > SNIPPET_LENGTH + ELLIPSIS.length()) {
    out.append(logMessage,errorEnd,errorEnd + SNIPPET_LENGTH).append(ELLIPSIS);
  }
 else {
    out.append(logMessage,errorEnd,logMessage.length());
  }
  return out.toString();
}
