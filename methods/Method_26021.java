@Nullable static DocTreePath getDocTreePath(VisitorState state){
  DocCommentTree docCommentTree=getDocCommentTree(state);
  if (docCommentTree == null) {
    return null;
  }
  return new DocTreePath(state.getPath(),docCommentTree);
}
