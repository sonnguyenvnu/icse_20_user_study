private static boolean shadowsClass(VisitorState state,TreePath treePath){
  if (!(treePath.getLeaf() instanceof IdentifierTree)) {
    return true;
  }
  TreePath enclosingClass=findPathFromEnclosingNodeToTopLevel(treePath,ClassTree.class);
  String name=((IdentifierTree)treePath.getLeaf()).getName().toString();
  return findIdent(name,state.withPath(enclosingClass),KindSelector.VAL_TYP) != null;
}
