/** 
 * Performs final indexing-building passes, including marking references to undeclared variables. Caller should invoke this method after loading all files.
 * @throws Exception
 */
public void finish() throws Exception {
  progress.end();
  Util.msg("Finished loading files. " + called.size() + " functions were called.");
  Util.msg("Analyzing uncalled functions, count: " + uncalled.size());
  applyUncalled();
  for (  List<Binding> lb : allBindings.values()) {
    for (    Binding b : lb) {
      if (!b.getType().isClassType() && !b.getType().isFuncType() && !b.getType().isModuleType() && b.getRefs().isEmpty()) {
        for (        Def def : b.getDefs()) {
          Indexer.idx.putProblem(def.getNode(),"Unused variable: " + def.getName());
        }
      }
    }
  }
  for (  Entry<Ref,List<Binding>> ent : locations.entrySet()) {
    convertCallToNew(ent.getKey(),ent.getValue());
  }
  nprob=problems.size();
  nparsing=parseErrs.size();
}
