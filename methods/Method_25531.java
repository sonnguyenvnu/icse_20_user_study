/** 
 * Finds all methods in any superclass of  {@code startClass} with a certain {@code name} thatmatch the given  {@code predicate}.
 * @return The (possibly empty) set of methods in any superclass that match {@code predicate} andhave the given  {@code name}.
 */
public static Set<MethodSymbol> findMatchingMethods(Name name,final Predicate<MethodSymbol> predicate,Type startClass,Types types){
  Filter<Symbol> matchesMethodPredicate=sym -> sym instanceof MethodSymbol && predicate.apply((MethodSymbol)sym);
  Set<MethodSymbol> matchingMethods=new HashSet<>();
  for (  Type superClass : types.closure(startClass)) {
    TypeSymbol superClassSymbol=superClass.tsym;
    Scope superClassSymbols=superClassSymbol.members();
    if (superClassSymbols != null) {
      for (      Symbol symbol : superClassSymbols.getSymbolsByName(name,matchesMethodPredicate,NON_RECURSIVE)) {
        matchingMethods.add((MethodSymbol)symbol);
      }
    }
  }
  return matchingMethods;
}
