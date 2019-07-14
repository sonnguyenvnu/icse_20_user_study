@Override public Description matchClass(ClassTree classTree,VisitorState state){
  DocTreePath path=Utils.getDocTreePath(state);
  if (path != null) {
    ImmutableSet<String> parameters=ImmutableSet.of();
    new InvalidTagChecker(state,JavadocTag.VALID_CLASS_TAGS,parameters).scan(path,null);
  }
  return Description.NO_MATCH;
}
