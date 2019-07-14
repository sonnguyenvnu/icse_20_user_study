/** 
 * Returns the message to be printed by the compiler when a match is found in interactive use. Includes the name of the check and a link for more information.
 */
public String getMessage(){
  return String.format("[%s] %s",checkName,getMessageWithoutCheckName());
}
