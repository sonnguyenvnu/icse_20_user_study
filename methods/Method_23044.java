/** 
 * Make list of package names by traversing a directory hierarchy. Each time a class is found in a folder, add its containing set of folders to the package list. If another folder is found, walk down into that folder and continue.
 */
static private void packageListFromFolder(File dir,String sofar,StringList list){
  boolean foundClass=false;
  String files[]=dir.list();
  for (int i=0; i < files.length; i++) {
    if (files[i].equals(".") || files[i].equals(".."))     continue;
    File sub=new File(dir,files[i]);
    if (sub.isDirectory()) {
      String nowfar=(sofar == null) ? files[i] : (sofar + "." + files[i]);
      packageListFromFolder(sub,nowfar,list);
    }
 else     if (!foundClass) {
      if (files[i].endsWith(".class")) {
        list.appendUnique(sofar);
        foundClass=true;
      }
    }
  }
}
