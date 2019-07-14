/** 
 * If type is Class<T>, returns the erasure of T. Otherwise, returns type unmodified. Returns empty() when provided a raw Class as argument.
 */
private static Optional<Type> argFromClass(Type type,VisitorState state){
  if (ASTHelpers.isSameType(type,state.getSymtab().classType,state)) {
    if (type.getTypeArguments().isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(state.getTypes().erasure(getOnlyElement(type.getTypeArguments())));
  }
  return Optional.of(type);
}
