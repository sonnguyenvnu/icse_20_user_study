/** 
 * Returns the list of imports specified by this library author. Only necessary for library authors that want to override the default behavior of importing all packages in their library.
 * @return null if no entries found
 */
static StringList parseImports(StringDict properties){
  StringList outgoing=new StringList();
  String importStr=properties.get(IMPORTS_PROPERTY);
  if (importStr != null) {
    String[] importList=PApplet.trim(PApplet.split(importStr,','));
    for (    String importName : importList) {
      if (!importName.isEmpty()) {
        outgoing.append(importName);
      }
    }
  }
  return (outgoing.size() > 0) ? outgoing : null;
}
