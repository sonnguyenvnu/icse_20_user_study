private boolean isValidArrayFill(VisitorState state,Type arrayComponentType,Type fillingObjectType){
  if (arrayComponentType == null || fillingObjectType == null) {
    return true;
  }
  return ASTHelpers.isSubtype(state.getTypes().boxedTypeOrType(fillingObjectType),arrayComponentType,state);
}
