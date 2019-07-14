/** 
 * A classpath, separated by the path separator, will contain a series of .jar/.zip files or directories containing .class files, or containing subdirectories that have .class files.
 * @param path the input classpath
 * @return array of possible package names
 */
static public StringList packageListFromClassPath(String path){
  StringList list=new StringList();
  String pieces[]=PApplet.split(path,File.pathSeparatorChar);
  for (int i=0; i < pieces.length; i++) {
    if (pieces[i].length() == 0)     continue;
    if (pieces[i].toLowerCase().endsWith(".jar") || pieces[i].toLowerCase().endsWith(".zip")) {
      packageListFromZip(pieces[i],list);
    }
 else {
      File dir=new File(pieces[i]);
      if (dir.exists() && dir.isDirectory()) {
        packageListFromFolder(dir,null,list);
      }
    }
  }
  StringList outgoing=new StringList(list.size());
  for (  String item : list) {
    outgoing.append(item.replace('/','.'));
  }
  return outgoing;
}
