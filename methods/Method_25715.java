static ImmutableList<Parameter> createListFromVarSymbols(List<VarSymbol> varSymbols){
  return Streams.mapWithIndex(varSymbols.stream(),(s,i) -> new AutoValue_Parameter(s.getSimpleName().toString(),s.asType(),(int)i,s.getSimpleName().toString(),Kind.IDENTIFIER,false)).collect(toImmutableList());
}
