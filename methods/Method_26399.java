private static boolean isInJodaTimeContext(VisitorState state){
  if (state.getPath().getParentPath() != null) {
    Tree parentLeaf=state.getPath().getParentPath().getLeaf();
    if (parentLeaf instanceof ExpressionTree && JODATIME_METHOD_MATCHER.matches((ExpressionTree)parentLeaf,state)) {
      return true;
    }
  }
  return false;
}
