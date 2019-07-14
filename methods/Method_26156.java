@Override public Description matchAssignment(AssignmentTree tree,VisitorState state){
  if (!boxingAssignment.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Assigning a primitive value to a non-primitive variable or array element" + " will autobox the value, which " + COMMON_MESSAGE_SUFFIX).build();
}
