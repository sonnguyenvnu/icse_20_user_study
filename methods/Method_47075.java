/** 
 * Whether a file exist at a specified path. We try to reload a list and conform from that list of parent's children that the file we're looking for is there or not.
 */
public static boolean fileExists(String path){
  File f=new File(path);
  String p=f.getParent();
  if (p != null && p.length() > 0) {
    ArrayList<HybridFileParcelable> ls=getFilesList(p,true,true,null);
    for (    HybridFileParcelable strings : ls) {
      if (strings.getPath() != null && strings.getPath().equals(path)) {
        return true;
      }
    }
  }
  return false;
}
