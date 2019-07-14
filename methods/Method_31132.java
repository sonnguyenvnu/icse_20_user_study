/** 
 * Retrieves the value this command-line argument tries to assign.
 * @param arg The command-line argument to check, typically in the form -key=value.
 * @return The value or an empty string if no value is assigned.
 */
static String getArgumentValue(String arg){
  int index=arg.indexOf("=");
  if ((index < 0) || (index == arg.length())) {
    return "";
  }
  return arg.substring(index + 1);
}
