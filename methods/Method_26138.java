@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (!IS_AUTOCLOSEABLE.matches(tree,state)) {
    return NO_MATCH;
  }
  for (  Tree member : tree.getMembers()) {
    if (!(member instanceof MethodTree)) {
      continue;
    }
    MethodTree methodTree=(MethodTree)member;
    if (!ASTHelpers.getSymbol(methodTree).isConstructor() || ASTHelpers.hasAnnotation(methodTree,MustBeClosed.class,state) || !invokedConstructorMustBeClosed(state,methodTree)) {
      continue;
    }
    if (ASTHelpers.isGeneratedConstructor(methodTree)) {
      state.reportMatch(buildDescription(tree).setMessage("Implicitly invoked constructor is marked @MustBeClosed, so this class must " + "have an explicit constructor with @MustBeClosed also.").build());
    }
 else {
      SuggestedFix.Builder builder=SuggestedFix.builder();
      String suggestedFixName=SuggestedFixes.qualifyType(state,builder,state.getTypeFromString(MustBeClosed.class.getCanonicalName()));
      SuggestedFix fix=builder.prefixWith(methodTree,"@" + suggestedFixName + " ").build();
      state.reportMatch(buildDescription(methodTree).addFix(fix).setMessage("Invoked constructor is marked @MustBeClosed, so this constructors must be " + "marked @MustBeClosed too.").build());
    }
  }
  return NO_MATCH;
}
