/** 
 * Find the path that contains modname. Used to find the starting point of locating a qname.
 * @param headName first module name segment
 */
public String locateModule(String headName){
  List<String> loadPath=getLoadPath();
  for (  String p : loadPath) {
    File startDir=new File(p,headName);
    File initFile=new File(_.joinPath(startDir,"__init__.py").getPath());
    if (initFile.exists()) {
      return p;
    }
    File startFile=new File(startDir + suffix);
    if (startFile.exists()) {
      return p;
    }
  }
  return null;
}
