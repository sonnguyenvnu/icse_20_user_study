@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  ImmutableList<ImportTree> wildcardImports=getWildcardImports(tree.getImports());
  if (wildcardImports.isEmpty()) {
    return NO_MATCH;
  }
  Set<TypeToImport> typesToImport=ImportCollector.collect((JCCompilationUnit)tree);
  Fix fix=createFix(wildcardImports,typesToImport,state);
  if (fix.isEmpty()) {
    return NO_MATCH;
  }
  return describeMatch(wildcardImports.get(0),fix);
}
