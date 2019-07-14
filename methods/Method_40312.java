/** 
 * Returns the innermost enclosing scope for doing (non-attribute) name lookups.  If the current node defines a scope, it returns the parent scope for name lookups.
 * @return the enclosing function, class, instance, module or builtin scope.If this node has not yet been resolved, returns the builtin namespace.
 */
public Scope getEnclosingNamespace(){
  if (parent == null || this.isModule()) {
    return Indexer.idx.globaltable;
  }
  Node up=this;
  while ((up=up.parent) != null) {
    if (up.isFunctionDef() || up.isClassDef() || up.isModule()) {
      Type type=up.getType();
      if (type == null || type.getTable() == null) {
        return Indexer.idx.globaltable;
      }
      return type.getTable();
    }
  }
  return Indexer.idx.globaltable;
}
