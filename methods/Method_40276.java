/** 
 * Import a module's alias (if present) or top-level name into the current scope.  Creates references to the other names in the alias.
 * @param mt the module that is actually bound to the imported name.for  {@code import x.y.z as foo}, it is  {@code z}, a sub-module of  {@code x.y}.  For  {@code import x.y.z} it is {@code x}.
 */
private void importName(Scope s,int tag,Alias a,ModuleType mt) throws Exception {
  if (a.aname != null) {
    if (mt.getFile() != null) {
      NameBinder.bind(s,a.aname,mt,tag);
    }
 else {
      s.update(a.aname.getId(),new Url(Builtins.LIBRARY_URL + mt.getTable().getPath() + ".html"),mt,Binding.Kind.SCOPE);
    }
  }
  addReferences(s,a.qname,true);
}
