/** 
 * Adds last LDC arguments to  {@link #getLastTwoStringArguments() string arguments}.
 */
private void keepStringArgument(final Object value){
  strArgs[0]=strArgs[1];
  strArgs[1]=value.toString();
}
