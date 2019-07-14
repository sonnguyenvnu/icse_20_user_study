private static boolean methodCallInDeclarationOfThrowingRunnable(VisitorState state){
  Tree tree=null;
  out:   for (  Tree t : state.getPath()) {
switch (t.getKind()) {
case LAMBDA_EXPRESSION:
case CLASS:
      tree=t;
    break out;
default :
}
}
if (tree == null) {
return false;
}
return isThrowingFunctionalInterface(state,ASTHelpers.getType(tree));
}
