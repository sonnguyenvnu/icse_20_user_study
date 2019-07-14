/** 
 * Retrieves the property this command-line argument tries to assign.
 * @param arg The command-line argument to check, typically in the form -key=value.
 * @return The property.
 */
static String getArgumentProperty(String arg){
  int index=arg.indexOf("=");
  return "flyway." + arg.substring(1,index);
}
