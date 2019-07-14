private static TreePath findEnclosingMethod(VisitorState state){
  TreePath path=state.getPath();
  while (path != null) {
switch (path.getLeaf().getKind()) {
case METHOD:
      return path;
case CLASS:
case LAMBDA_EXPRESSION:
    return null;
default :
}
path=path.getParentPath();
}
return null;
}
