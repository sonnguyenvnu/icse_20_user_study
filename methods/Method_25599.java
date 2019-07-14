/** 
 * @param symStr the string representation of a symbol
 * @return the Symbol object, or null if it cannot be found
 */
@Nullable public Symbol getSymbolFromString(String symStr){
  symStr=inferBinaryName(symStr);
  Name name=getName(symStr);
  boolean modular=sharedState.modules.getDefaultModule() != getSymtab().noModule;
  if (!modular) {
    return getSymbolFromString(getSymtab().noModule,name);
  }
  for (  ModuleSymbol msym : sharedState.modules.allModules()) {
    ClassSymbol result=getSymbolFromString(msym,name);
    if (result != null) {
      return result;
    }
  }
  return null;
}
