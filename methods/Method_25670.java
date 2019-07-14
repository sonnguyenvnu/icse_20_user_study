private static String joinSource(VisitorState state,List<AnnotationTree> moveBefore){
  return moveBefore.stream().map(state::getSourceForNode).collect(joining(" "));
}
