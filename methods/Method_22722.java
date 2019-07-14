/** 
 * Convert to sanitized name and alert the user if changes were made.
 */
static public String checkName(String origName){
  String newName=sanitizeName(origName);
  if (!newName.equals(origName)) {
    String msg=Language.text("check_name.messages.is_name_modified");
    System.out.println(msg);
  }
  return newName;
}
