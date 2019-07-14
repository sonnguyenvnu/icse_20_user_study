private String getClassName(Type type){
  if (type == null) {
    return null;
  }
  ResolvableType resolvableType=ResolvableType.forType(type);
  return resolvableType.resolve().getName();
}
