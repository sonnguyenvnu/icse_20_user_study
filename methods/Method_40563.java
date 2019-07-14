/** 
 * Determines the fully-qualified module name for the specified file.  A module's qname is a function of the module's absolute path and the sys path; it does not depend on how the module name may have been specified in import statements. The module qname is the relative path of the module under the load path, with slashes converted to dots.
 * @param file absolute canonical path to a file (__init__.py for dirs)
 * @return null if {@code file} is not somewhere under the load path
 */
public static String moduleQname(String file){
  boolean initpy=file.endsWith("/__init__.py");
  if (initpy) {
    file=file.substring(0,file.length() - "/__init__.py".length());
  }
 else   if (file.endsWith(".py")) {
    file=file.substring(0,file.length() - ".py".length());
  }
  for (  String root : Indexer.idx.getLoadPath()) {
    if (file.startsWith(root)) {
      return file.substring(root.length()).replace('/','.');
    }
  }
  return null;
}
