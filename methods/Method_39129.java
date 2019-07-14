/** 
 * Prints out the message. User can override this method and modify the way the message is printed.
 */
protected void printBefore(final ActionRequest request){
  StringBuilder message=new StringBuilder(prefixIn);
  message.append(request.getActionPath()).append("   [").append(request.getActionRuntime().createActionString()).append(']');
  out(message.toString());
}
