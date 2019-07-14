@Override public Description matchTypeCast(TypeCastTree tree,VisitorState state){
  if (!boxingCast.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Casting a primitive value to a non-primitive type will autobox the value," + " which " + COMMON_MESSAGE_SUFFIX).build();
}
