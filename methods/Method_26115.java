private static boolean collectionUsed(VisitorState state){
  TreePath path=state.getPath();
  return !(path.getParentPath().getLeaf() instanceof MemberSelectTree) || !(path.getParentPath().getParentPath().getLeaf() instanceof MethodInvocationTree) || !COLLECTION_SETTER.matches((MethodInvocationTree)path.getParentPath().getParentPath().getLeaf(),state) || ASTHelpers.targetType(state.withPath(path.getParentPath().getParentPath())) != null;
}
