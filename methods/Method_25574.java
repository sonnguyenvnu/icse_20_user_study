/** 
 * Finds all the visible fields declared or inherited in the target class 
 */
public static List<VarSymbol> findAllFields(Type classType,VisitorState state){
  return state.getTypes().closure(classType).stream().flatMap(type -> {
    TypeSymbol tsym=type.tsym;
    if (tsym == null) {
      return ImmutableList.<VarSymbol>of().stream();
    }
    WriteableScope scope=tsym.members();
    if (scope == null) {
      return ImmutableList.<VarSymbol>of().stream();
    }
    return ImmutableList.copyOf(scope.getSymbols(VarSymbol.class::isInstance)).reverse().stream().map(v -> (VarSymbol)v).filter(v -> isVisible(v,state.getPath()));
  }
).collect(Collectors.toCollection(ArrayList::new));
}
