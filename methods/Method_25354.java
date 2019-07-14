/** 
 * Returns the message, not including the check name but including the link. 
 */
public String getMessageWithoutCheckName(){
  return linkUrl != null ? String.format("%s\n%s",rawMessage,linkTextForDiagnostic(linkUrl)) : String.format("%s",rawMessage);
}
