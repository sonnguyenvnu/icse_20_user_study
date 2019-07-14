private void checkForEmptyBlockTags(VisitorState state){
  DocTreePath path=Utils.getDocTreePath(state);
  if (path != null) {
    new EmptyBlockTagChecker(state).scan(path,null);
  }
}
