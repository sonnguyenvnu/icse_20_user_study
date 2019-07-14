@Override public Description matchUnary(UnaryTree tree,VisitorState state){
  if (!boxingUnary.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Pre- and post- increment/decrement operations on a non-primitive variable or" + " array element will autobox the result, which " + COMMON_MESSAGE_SUFFIX).build();
}
