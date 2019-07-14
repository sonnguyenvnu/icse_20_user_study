private static ImmutableList<TypeAndSymbol> expandVarargsToArity(List<VarSymbol> formalArgs,int arity){
  ImmutableList.Builder<TypeAndSymbol> result=ImmutableList.builderWithExpectedSize(arity);
  int numberOfVarArgs=arity - formalArgs.size() + 1;
  for (Iterator<VarSymbol> argsIterator=formalArgs.iterator(); argsIterator.hasNext(); ) {
    VarSymbol arg=argsIterator.next();
    if (argsIterator.hasNext()) {
      result.add(TypeAndSymbol.create(arg.type,arg));
    }
 else {
      Type varArgType=((ArrayType)arg.type).elemtype;
      for (int idx=0; idx < numberOfVarArgs; idx++) {
        result.add(TypeAndSymbol.create(varArgType));
      }
    }
  }
  return result.build();
}
