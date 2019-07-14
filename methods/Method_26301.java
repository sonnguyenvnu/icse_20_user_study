/** 
 * Ignore some common ThreadLocal type arguments that are fine to have per-instance copies of. 
 */
private boolean wellKnownTypeArgument(NewClassTree tree,VisitorState state){
  Type type=getType(tree);
  if (type == null) {
    return false;
  }
  type=state.getTypes().asSuper(type,state.getSymbolFromString("java.lang.ThreadLocal"));
  if (type == null) {
    return false;
  }
  if (type.getTypeArguments().isEmpty()) {
    return false;
  }
  Type argType=getOnlyElement(type.getTypeArguments());
  if (WELL_KNOWN_TYPES.contains(argType.asElement().getQualifiedName().toString())) {
    return true;
  }
  if (isSubtype(argType,state.getTypeFromString("java.text.DateFormat"),state)) {
    return true;
  }
  return false;
}
