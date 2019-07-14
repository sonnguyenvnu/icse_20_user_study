@Override public Description matchTypeParameter(TypeParameterTree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return NO_MATCH;
  }
  ImmutableAnalysis analysis=createImmutableAnalysis(state);
  if (!analysis.hasThreadSafeTypeParameterAnnotation((TypeVariableSymbol)sym)) {
    return NO_MATCH;
  }
switch (sym.owner.getKind()) {
case METHOD:
case CONSTRUCTOR:
    return NO_MATCH;
default :
}
AnnotationInfo info=analysis.getImmutableAnnotation(sym.owner,state);
if (info == null) {
  return buildDescription(tree).setMessage("@Immutable is only supported on immutable classes").build();
}
return NO_MATCH;
}
