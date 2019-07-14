/** 
 * Given an absolute  {@code path} to a file (not a directory),returns the module name for the file.  If the file is an __init__.py, returns the last component of the file's parent directory, else returns the filename without path or extension.
 */
public static String moduleNameFor(String path){
  File f=new File(path);
  if (f.isDirectory()) {
    throw new IllegalStateException("failed assertion: " + path);
  }
  String fname=f.getName();
  if (fname.equals("__init__.py")) {
    return f.getParentFile().getName();
  }
  return fname.substring(0,fname.lastIndexOf('.'));
}
