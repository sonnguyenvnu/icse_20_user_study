/** 
 * Returns the global scope (i.e. the module scope for the current module).
 */
public Scope getGlobalTable(){
  Scope result=getSymtabOfType(ScopeType.MODULE);
  if (result == null) {
    Indexer.idx.reportFailedAssertion("No module table found for " + this);
    result=this;
  }
  return result;
}
