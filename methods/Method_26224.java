@Override public Description matchClass(ClassTree classTree,VisitorState state){
  if (!classTree.getKind().equals(CLASS) || classTree.getExtendsClause() != null || !classTree.getImplementsClause().isEmpty() || isInPrivateScope(state) || hasAnnotation(getSymbol(classTree),"org.junit.runner.RunWith",state)) {
    return NO_MATCH;
  }
  FluentIterable<? extends Tree> nonSyntheticMembers=FluentIterable.from(classTree.getMembers()).filter(Predicates.not(new Predicate<Tree>(){
    @Override public boolean apply(    Tree tree){
      return tree.getKind().equals(METHOD) && isGeneratedConstructor((MethodTree)tree);
    }
  }
));
  if (nonSyntheticMembers.isEmpty()) {
    return NO_MATCH;
  }
  boolean isUtilityClass=nonSyntheticMembers.allMatch(new Predicate<Tree>(){
    @Override public boolean apply(    Tree tree){
switch (tree.getKind()) {
case CLASS:
        return ((ClassTree)tree).getModifiers().getFlags().contains(STATIC);
case METHOD:
      return ((MethodTree)tree).getModifiers().getFlags().contains(STATIC);
case VARIABLE:
    return ((VariableTree)tree).getModifiers().getFlags().contains(STATIC);
case BLOCK:
  return ((BlockTree)tree).isStatic();
case ENUM:
case ANNOTATION_TYPE:
case INTERFACE:
return true;
default :
throw new AssertionError("unknown member type:" + tree.getKind());
}
}
}
);
if (!isUtilityClass) {
return NO_MATCH;
}
return describeMatch(classTree,addMembers(classTree,state,"private " + classTree.getSimpleName() + "() {}"));
}
