/** 
 * This method searches the module path for the module  {@code modname}. If found, it is passed to  {@link #loadFile}. <p>The mechanisms for importing modules are in general statically undecidable.  We make a reasonable effort to follow the most common lookup rules.
 * @param modname a module name.   Can be a relative path to a directoryor a file (without the extension) or a possibly-qualified module name.  If it is a module name, cannot contain leading dots.
 * @see http://docs.python.org/reference/simple_stmts.html#the-import-statement
 */
public ModuleType loadModule(String modname) throws Exception {
  if (failedModules.contains(modname)) {
    return null;
  }
  ModuleType cached=getCachedModule(modname);
  if (cached != null) {
    finer("using cached module " + modname);
    return cached;
  }
  ModuleType mt=getBuiltinModule(modname);
  if (mt != null) {
    return mt;
  }
  finer("looking for module " + modname);
  if (modname.endsWith(".py")) {
    modname=modname.substring(0,modname.length() - 3);
  }
  String modpath=modname.replace('.','/');
  modpath=modpath.replaceFirst("(/python[23])/([0-9]/)","$1.$2");
  List<String> loadPath=getLoadPath();
  for (  String p : loadPath) {
    String dirname=p + modpath;
    String pyname=dirname + ".py";
    String initname=Util.joinPath(dirname,"__init__.py").getAbsolutePath();
    String name;
    if (Util.isReadableFile(initname)) {
      name=initname;
    }
 else     if (Util.isReadableFile(pyname)) {
      name=pyname;
    }
 else {
      continue;
    }
    name=Util.canonicalize(name);
    ModuleType m=loadFile(name);
    if (m != null) {
      finer("load of module " + modname + "[succeeded]");
      return m;
    }
  }
  finer("failed to find module " + modname + " in load path");
  failedModules.add(modname);
  return null;
}
