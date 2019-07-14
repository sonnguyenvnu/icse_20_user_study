/** 
 * Load all Python source files recursively if the given fullname is a directory; otherwise just load a file.  Looks at file extension to determine whether to load a given file.
 */
public void loadFileRecursive(String fullname){
  int count=countFileRecursive(fullname);
  if (loadingProgress == null) {
    loadingProgress=new Progress(count,50);
  }
  File file_or_dir=new File(fullname);
  if (file_or_dir.isDirectory()) {
    for (    File file : file_or_dir.listFiles()) {
      loadFileRecursive(file.getPath());
    }
  }
 else {
    if (file_or_dir.getPath().endsWith(suffix)) {
      loadFile(file_or_dir.getPath());
    }
  }
}
