/** 
 * Check a single field for immutability. 
 */
private Violation isFieldImmutable(Optional<Tree> tree,ImmutableSet<String> immutableTyParams,ClassSymbol classSym,ClassType classType,VarSymbol var,ViolationReporter reporter){
  if (bugChecker.isSuppressed(var)) {
    return Violation.absent();
  }
  if (!var.getModifiers().contains(Modifier.FINAL) && !ASTHelpers.hasAnnotation(var,LazyInit.class,state)) {
    Violation info=Violation.of(String.format("'%s' has non-final field '%s'",threadSafety.getPrettyName(classSym),var.getSimpleName()));
    if (tree.isPresent()) {
      state.reportMatch(reporter.report(tree.get(),info,SuggestedFixes.addModifiers(tree.get(),state,Modifier.FINAL)));
      return Violation.absent();
    }
    return info;
  }
  Type varType=state.getTypes().memberType(classType,var);
  Violation info=threadSafety.isThreadSafeType(true,immutableTyParams,varType);
  if (info.isPresent()) {
    info=info.plus(String.format("'%s' has field '%s' of type '%s'",threadSafety.getPrettyName(classSym),var.getSimpleName(),varType));
    if (tree.isPresent()) {
      state.reportMatch(reporter.report(tree.get(),info,Optional.empty()));
      return Violation.absent();
    }
    return info;
  }
  return Violation.absent();
}
