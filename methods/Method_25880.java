@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  VariableAssignmentRecords writes=new VariableAssignmentRecords();
  new FinalScanner(writes,state.context).scan(state.getPath(),InitializationContext.NONE);
  outer:   for (  VariableAssignments var : writes.getAssignments()) {
    if (!var.isEffectivelyFinal()) {
      continue;
    }
    if (!var.sym.isPrivate()) {
      continue;
    }
    for (    String annotation : IMPLICIT_VAR_ANNOTATIONS) {
      if (ASTHelpers.hasAnnotation(var.sym,annotation,state)) {
        continue outer;
      }
    }
    VariableTree varDecl=var.declaration();
    for (    AnnotationTree anno : varDecl.getModifiers().getAnnotations()) {
      if (IMPLICIT_VAR_ANNOTATION_SIMPLE_NAMES.contains(ASTHelpers.getAnnotationName(anno))) {
        return Description.NO_MATCH;
      }
    }
    SuggestedFixes.addModifiers(varDecl,state,Modifier.FINAL).ifPresent(f -> {
      if (SuggestedFixes.compilesWithFix(f,state)) {
        state.reportMatch(describeMatch(varDecl,f));
      }
    }
);
  }
  return Description.NO_MATCH;
}
