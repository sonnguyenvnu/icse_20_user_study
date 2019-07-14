private static boolean implementsImmutableInterface(ClassSymbol symbol){
  return Streams.concat(symbol.getInterfaces().stream(),Stream.of(symbol.getSuperclass())).anyMatch(supertype -> supertype.asElement().getAnnotation(Immutable.class) != null);
}
