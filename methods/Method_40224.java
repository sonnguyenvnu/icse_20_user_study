/** 
 * Given an absolute  {@code path} to a file (not a directory),returns the module name for the file.  If the file is an __init__.py, returns the last component of the file's parent directory, else returns the filename without path or extension.
 */
public static String moduleName(String path){
  File f=new File(path);
  String name=f.getName();
  if (name.equals("__init__.py")) {
    return f.getParentFile().getName();
  }
 else   if (name.endsWith(Analyzer.self.suffix)) {
    return name.substring(0,name.length() - Analyzer.self.suffix.length());
  }
 else {
    return name;
  }
}
