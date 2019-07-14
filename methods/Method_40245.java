/** 
 * Resolves and returns the referenced {@link org.python.indexer.types.ModuleType} in an import oror import-from statement.  NImportFrom statements manually resolve their child NAliases.
 */
@Override public Type resolve(Scope s,int tag) throws Exception {
  Type t=resolveExpr(qname,s,tag);
  if (aname != null && qname != null) {
    t=qname.getBottom().getType();
  }
  return t;
}
