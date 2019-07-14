public void finish(){
  _.msg("\nFinished loading files. " + nCalled + " functions were called.");
  _.msg("Analyzing uncalled functions");
  applyUncalled();
  for (  Binding b : allBindings) {
    if (!(b.type instanceof ClassType) && !(b.type instanceof FunType) && !(b.type instanceof ModuleType) && b.refs.isEmpty()) {
      Analyzer.self.putProblem(b.node,"Unused variable: " + b.name);
    }
  }
  _.msg(getAnalysisSummary());
}
