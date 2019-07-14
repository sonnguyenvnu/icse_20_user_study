private static ImmutableMap<TypeVariableSymbol,Type> getInstantiation(Types types,Type methodType){
  List<Type> to=new ArrayList<>();
  ArrayList<Type> from=new ArrayList<>();
  getSubst(getMapping(methodType),from,to);
  Map<TypeVariableSymbol,Type> mapping=new LinkedHashMap<>();
  Streams.forEachPair(from.stream(),to.stream(),(f,t) -> {
    Type existing=mapping.put((TypeVariableSymbol)f.asElement(),t);
    if (existing != null && !types.isSameType(t,existing)) {
      throw new AssertionError(String.format("%s instantiated as both %s and %s",f.asElement(),t,existing));
    }
  }
);
  return ImmutableMap.copyOf(mapping);
}
