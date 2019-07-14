@Override public Description matchNewClass(NewClassTree newClassTree,VisitorState state){
  if (state.getPath().getParentPath().getLeaf().getKind() != Kind.EXPRESSION_STATEMENT) {
    return Description.NO_MATCH;
  }
  if (newClassTree.getClassBody() == null) {
    return Description.NO_MATCH;
  }
  if (!newClassTree.getArguments().isEmpty()) {
    return Description.NO_MATCH;
  }
  for (  Tree def : newClassTree.getClassBody().getMembers()) {
switch (def.getKind()) {
case VARIABLE:
{
        VariableTree variableTree=(VariableTree)def;
        if (variableTree.getInitializer() != null) {
          return Description.NO_MATCH;
        }
        break;
      }
case BLOCK:
    return Description.NO_MATCH;
default :
  break;
}
}
if (!sideEffectFreeConstructor(((JCTree)newClassTree.getIdentifier()).type.tsym,state)) {
return Description.NO_MATCH;
}
return describeMatch(newClassTree);
}
