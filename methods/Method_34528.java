private static Optional<Type> getParentKind(Type type,List<Type> types){
  int pos=position(type,types);
  if (pos <= 0)   return Optional.absent();
  return Optional.of(types.get(pos - 1));
}
