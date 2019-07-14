/** 
 * Releases all resources for the current indexer.
 */
public void release(){
  moduleTable=globaltable=null;
  clearAstCache();
  astCache=null;
  locations=null;
  problems.clear();
  problems=null;
  parseErrs.clear();
  parseErrs=null;
  path.clear();
  path=null;
  failedModules.clear();
  failedModules=null;
  unresolvedModules.clear();
  unresolvedModules=null;
  builtins=null;
  allBindings.clear();
  allBindings=null;
  uncalled.clear();
  called.clear();
  idx=null;
}
