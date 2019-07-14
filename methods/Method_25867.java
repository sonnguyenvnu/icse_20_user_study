private static List<Type> typeArgsAsSuper(Type baseType,Type superType,VisitorState state){
  Type projectedType=state.getTypes().asSuper(baseType,superType.tsym);
  if (projectedType != null) {
    return projectedType.getTypeArguments();
  }
  return new ArrayList<>();
}
