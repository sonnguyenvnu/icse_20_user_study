/** 
 * Prepares resource and class names. For classes, it strips '.class' from the end and converts all (back)slashes to dots. For resources, it replaces all backslashes to slashes.
 */
protected String prepareEntryName(final String name,final boolean isClass){
  String entryName=name;
  if (isClass) {
    entryName=name.substring(0,name.length() - 6);
    entryName=StringUtil.replaceChar(entryName,'/','.');
    entryName=StringUtil.replaceChar(entryName,'\\','.');
  }
 else {
    entryName='/' + StringUtil.replaceChar(entryName,'\\','/');
  }
  return entryName;
}
