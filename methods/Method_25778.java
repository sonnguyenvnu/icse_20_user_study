private static MethodTree findEnclosing(TreePath path){
  for (  Tree tree : path) {
switch (tree.getKind()) {
case METHOD:
      return (MethodTree)tree;
case LAMBDA_EXPRESSION:
case CLASS:
    return null;
default :
}
}
return null;
}
