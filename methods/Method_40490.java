/** 
 * Load all Python source files recursively if the given fullname is a directory; otherwise just load a file.  Looks at file extension to determine whether to load a given file.
 */
public void loadFileRecursive(String fullname) throws Exception {
  File file_or_dir=new File(fullname);
  if (file_or_dir.isDirectory()) {
    setProjectDir(fullname);
    for (    File file : file_or_dir.listFiles()) {
      loadFileRecursive(file.getAbsolutePath());
    }
  }
 else {
    if (file_or_dir.getAbsolutePath().endsWith(".py")) {
      setProjectDir(file_or_dir.getParent());
      loadFile(file_or_dir.getAbsolutePath());
    }
  }
}
