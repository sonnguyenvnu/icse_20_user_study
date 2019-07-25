/** 
 * Returns a file obtained by some standard method from the string name.  For example, resolve("Foo") might equal the file "/udir/luser/current/path/Foo.tla". Returns null if the file does not exist
 */
public File resolve(String name,boolean isModule){
  int n;
  n=name.indexOf('\n');
  if (n >= 0) {
    ToolIO.out.println("*** Warning: module name '" + name + "' contained NEWLINE; " + "Only the part before NEWLINE is considered.");
    name=name.substring(0,n);
  }
  String sourceFileName=null;
  if (isModule) {
    if (name.toLowerCase().endsWith(".tla")) {
      name=name.substring(0,name.length() - 4);
    }
    sourceFileName=name + ".tla";
  }
 else {
    sourceFileName=name;
  }
  return locate(sourceFileName);
}
