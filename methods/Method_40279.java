/** 
 * Resolve "foo [as bar]" in "from x[.y] import foo [as bar]". There are several possibilities for "foo":  it could be a file in the directory "x[.y]", or it could be a name exported by the module "x[.y]" (from its __init__.py), or it could be a public name in the file "x/y.py".
 * @param scope the scope into which names should be imported
 * @param mt the non-{@code null} module "x[.y]".  Could refer toeither x/y.py or x/y/__init__.py.
 * @param a the "foo [as bar]" component of the import statement
 */
private void resolveAlias(Scope scope,ModuleType mt,Alias a) throws Exception {
  Binding entry=mt.getTable().lookup(a.name);
  if (entry == null) {
    String mqname=qname.toQname() + "." + a.qname.toQname();
    ModuleType mt2=Indexer.idx.loadModule(mqname);
    if (mt2 != null) {
      entry=Indexer.idx.lookupFirstBinding(mt2.getTable().getPath());
    }
  }
  if (entry == null) {
    addError(a,"name " + a.qname.getName().getId() + " not found in module " + this.module);
    return;
  }
  String qname=a.qname.getName().getId();
  String aname=a.aname != null ? a.aname.getId() : null;
  Indexer.idx.putLocation(a.qname.getName(),entry);
  if (aname != null) {
    Indexer.idx.putLocation(a.aname,entry);
    scope.put(aname,entry);
  }
 else {
    scope.put(qname,entry);
  }
}
