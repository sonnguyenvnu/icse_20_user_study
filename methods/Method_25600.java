@Nullable public ClassSymbol getSymbolFromString(ModuleSymbol msym,Name name){
  ClassSymbol result=getSymtab().getClass(msym,name);
  if (result == null || result.kind == Kind.ERR || !result.exists()) {
    return null;
  }
  try {
    result.complete();
  }
 catch (  CompletionFailure failure) {
    return null;
  }
  return result;
}
