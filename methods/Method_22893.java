/** 
 * Get the JEditTextArea object for use (not recommended). This should only be used in obscure cases that really need to hack the internals of the JEditTextArea. Most tools should only interface via the get/set functions found in this class. This will maintain compatibility with future releases, which will not use JEditTextArea.
 */
public JEditTextArea getTextArea(){
  return textarea;
}
