private Symbol.VarSymbol getField(Symbol classSymbol,String name){
  return getMember(Symbol.VarSymbol.class,ElementKind.FIELD,classSymbol,name);
}
