/** 
 * Prints out the message. User can override this method and modify the way the message is printed.
 */
protected void printAfter(final ActionRequest request,final long executionTime,final Object result){
  StringBuilder message=new StringBuilder(prefixOut);
  String resultString=StringUtil.toSafeString(result);
  if (resultString.length() > 70) {
    resultString=resultString.substring(0,70);
    resultString+="...";
  }
  message.append(request.getActionPath()).append("  (").append(resultString).append(") in ").append(executionTime).append("ms.");
  out(message.toString());
}
