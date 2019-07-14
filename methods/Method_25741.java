@Override public Description matchClass(ClassTree classTree,final VisitorState state){
  if (!CLASS_TREE_MATCHER.matches(classTree,state)) {
    return Description.NO_MATCH;
  }
  if (classTree.getKind() == Kind.ENUM) {
    return buildDescription(classTree).setMessage("Enums cannot correctly implement Annotation because their equals and hashCode " + "methods are final. Consider using AutoAnnotation instead of implementing " + "Annotation by hand.").build();
  }
  MethodSymbol equals=null;
  MethodSymbol hashCode=null;
  final Types types=state.getTypes();
  Name equalsName=state.getName("equals");
  Predicate<MethodSymbol> equalsPredicate=new Predicate<MethodSymbol>(){
    @Override public boolean apply(    MethodSymbol methodSymbol){
      return !methodSymbol.isStatic() && ((methodSymbol.flags() & Flags.SYNTHETIC) == 0) && ((methodSymbol.flags() & Flags.ABSTRACT) == 0) && methodSymbol.getParameters().size() == 1 && types.isSameType(methodSymbol.getParameters().get(0).type,state.getSymtab().objectType);
    }
  }
;
  Name hashCodeName=state.getName("hashCode");
  Predicate<MethodSymbol> hashCodePredicate=new Predicate<MethodSymbol>(){
    @Override public boolean apply(    MethodSymbol methodSymbol){
      return !methodSymbol.isStatic() && ((methodSymbol.flags() & Flags.SYNTHETIC) == 0) && ((methodSymbol.flags() & Flags.ABSTRACT) == 0) && methodSymbol.getParameters().isEmpty();
    }
  }
;
  for (  Type sup : types.closure(ASTHelpers.getSymbol(classTree).type)) {
    if (equals == null) {
      equals=getMatchingMethod(sup,equalsName,equalsPredicate);
    }
    if (hashCode == null) {
      hashCode=getMatchingMethod(sup,hashCodeName,hashCodePredicate);
    }
  }
  Verify.verifyNotNull(equals);
  Verify.verifyNotNull(hashCode);
  Symbol objectSymbol=state.getSymtab().objectType.tsym;
  if (equals.owner.equals(objectSymbol) || hashCode.owner.equals(objectSymbol)) {
    return describeMatch(classTree);
  }
  return Description.NO_MATCH;
}
