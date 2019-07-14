private TreePath findEnclosing(VisitorState state){
  for (TreePath path=state.getPath(); path != null; path=path.getParentPath()) {
switch (path.getLeaf().getKind()) {
case METHOD:
case LAMBDA_EXPRESSION:
      return path;
case CLASS:
    return null;
default :
}
}
return null;
}
