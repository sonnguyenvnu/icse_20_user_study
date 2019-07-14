/** 
 * Returns a list of type symbols in the scope enclosing  {@code env} guaranteeing that, when twosymbols share a simple name, the shadower precedes the shadow-ee
 */
private static Iterable<Symbol> typesInEnclosingScope(Env<AttrContext> env,PackageSymbol javaLang){
  Iterable<Symbol> localSymbolsInScope=Streams.stream(env).map(ctx -> ctx.tree.getTag() == Tag.CLASSDEF ? ((ClassSymbol)ASTHelpers.getSymbol(ctx.tree)).members().getSymbols() : ctx.info.getLocalElements()).flatMap(symbols -> Streams.stream(symbols).filter(sym -> sym instanceof TypeSymbol && !(sym instanceof TypeVariableSymbol))).collect(ImmutableList.toImmutableList());
  return Iterables.concat(localSymbolsInScope,env.toplevel.namedImportScope.getSymbols(),javaLang.members().getSymbols(),env.toplevel.packge.members().getSymbols());
}
