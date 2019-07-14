/** 
 * Returns action string in form 'actionClass#actionMethod'.
 */
public String createActionString(){
  if (actionHandler != null) {
    return actionHandler.getClass().getName();
  }
  String className=actionClass.getName();
  final int ndx=className.indexOf("$$");
  if (ndx != -1) {
    className=className.substring(0,ndx);
  }
  return className + '#' + actionClassMethod.getName();
}
