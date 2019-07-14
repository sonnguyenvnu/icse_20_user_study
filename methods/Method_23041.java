/** 
 * Given a folder, return a list of absolute paths to all jar or zip files inside that folder, separated by pathSeparatorChar. This will prepend a colon (or whatever the path separator is) so that it can be directly appended to another path string. As of 0136, this will no longer add the root folder as well. This function doesn't bother checking to see if there are any .class files in the folder or within a subfolder.
 */
static public String contentsToClassPath(File folder){
  if (folder == null)   return "";
  StringBuilder sb=new StringBuilder();
  String sep=System.getProperty("path.separator");
  try {
    String path=folder.getCanonicalPath();
    if (!path.endsWith(File.separator)) {
      path+=File.separator;
    }
    String list[]=folder.list();
    for (int i=0; i < list.length; i++) {
      if (list[i].startsWith("."))       continue;
      if (list[i].toLowerCase().endsWith(".jar") || list[i].toLowerCase().endsWith(".zip")) {
        sb.append(sep);
        sb.append(path);
        sb.append(list[i]);
      }
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return sb.toString();
}
