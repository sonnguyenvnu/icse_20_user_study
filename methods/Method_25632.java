/** 
 * Returns the enclosing method of the given visitor state. Returns null if the state is within a lambda expression or anonymous class.
 */
@Nullable private static MethodTree enclosingMethod(VisitorState state){
  for (  Tree node : state.getPath().getParentPath()) {
switch (node.getKind()) {
case LAMBDA_EXPRESSION:
case NEW_CLASS:
      return null;
case METHOD:
    return (MethodTree)node;
default :
  break;
}
}
return null;
}
