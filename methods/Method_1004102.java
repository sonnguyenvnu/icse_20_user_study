private void check(final IClassCoverage classCoverage){
  final String name=names.getQualifiedClassName(classCoverage.getName());
  checkRules(classCoverage,classRules,"class",name);
  if (traverseMethods) {
    for (    final IMethodCoverage m : classCoverage.getMethods()) {
      check(m,classCoverage.getName());
    }
  }
}
