public void finish(){
  _.msg("\nFinished loading files. " + nCalled + " functions were called.");
  _.msg("Analyzing uncalled functions");
  applyUncalled();
  for (  Binding b : allBindings) {
    if (!b.type.isClassType() && !b.type.isFuncType() && !b.type.isModuleType() && b.refs.isEmpty()) {
      Analyzer.self.putProblem(b.node,"Unused variable: " + b.node.name);
    }
  }
  _.msg(getAnalysisSummary());
}
