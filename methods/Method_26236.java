@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (!IS_BUGCHECKER.matches(tree,state)) {
    return NO_MATCH;
  }
  AnnotationTree bugPatternAnnotation=ASTHelpers.getAnnotationWithSimpleName(tree.getModifiers().getAnnotations(),"BugPattern");
  if (bugPatternAnnotation == null) {
    return NO_MATCH;
  }
  if (!providesFix(tree,state)) {
    return NO_MATCH;
  }
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder().addImport("com.google.errorprone.BugPattern.ProvidesFix");
  ExpressionTree providesFixArgument=AnnotationMatcherUtils.getArgument(bugPatternAnnotation,"providesFix");
  if (providesFixArgument == null) {
    fixBuilder.postfixWith(Iterables.getLast(bugPatternAnnotation.getArguments()),", providesFix = ProvidesFix." + REQUIRES_HUMAN_ATTENTION.name());
  }
 else {
    if (!getSymbol(providesFixArgument).getSimpleName().contentEquals(NO_FIX.name())) {
      return NO_MATCH;
    }
    fixBuilder.replace(providesFixArgument,"ProvidesFix." + REQUIRES_HUMAN_ATTENTION.name());
  }
  return describeMatch(bugPatternAnnotation,fixBuilder.build());
}
