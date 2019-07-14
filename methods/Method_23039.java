/** 
 * Calculate the size of the contents of a folder. Used to determine whether sketches are empty or not. Note that the function calls itself recursively.
 */
static public long calcFolderSize(File folder){
  int size=0;
  String files[]=folder.list();
  if (files == null)   return -1;
  for (int i=0; i < files.length; i++) {
    if (files[i].equals(".") || files[i].equals("..") || files[i].equals(".DS_Store"))     continue;
    File fella=new File(folder,files[i]);
    if (fella.isDirectory()) {
      size+=calcFolderSize(fella);
    }
 else {
      size+=(int)fella.length();
    }
  }
  return size;
}
