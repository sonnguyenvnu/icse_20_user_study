private static Multimap<TypeVariableSymbol,TypeInfo> getResolvedGenerics(MethodInvocationTree tree){
  Type type=ASTHelpers.getType(tree.getMethodSelect());
  List<Type> from=new ArrayList<>();
  List<Type> to=new ArrayList<>();
  getSubst(type,from,to);
  Multimap<TypeVariableSymbol,TypeInfo> result=Streams.zip(from.stream(),to.stream(),(f,t) -> new TypeInfo((TypeVariableSymbol)f.asElement(),t,tree)).collect(toMultimap(k -> k.sym,k -> k,MultimapBuilder.linkedHashKeys().arrayListValues()::build));
  return result;
}
