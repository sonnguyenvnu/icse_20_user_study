@Override public Description matchVariable(VariableTree tree,VisitorState state){
  if (!boxingInitialization.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Initializing a non-primitive variable with a primitive value will autobox the" + " value, which " + COMMON_MESSAGE_SUFFIX).build();
}
