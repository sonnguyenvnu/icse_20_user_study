private static MethodTree enclosingMethod(VisitorState state){
  for (  Tree parent : state.getPath()) {
switch (parent.getKind()) {
case METHOD:
      return (MethodTree)parent;
case CLASS:
case LAMBDA_EXPRESSION:
    return null;
default :
}
}
return null;
}
