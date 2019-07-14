/** 
 * Finds all variable declarations which are unused at this point in the AST (i.e. they might be used further on).
 */
public static ImmutableSet<VarSymbol> findUnusedIdentifiers(VisitorState state){
  ImmutableSet.Builder<VarSymbol> definedVariables=ImmutableSet.builder();
  ImmutableSet.Builder<Symbol> usedSymbols=ImmutableSet.builder();
  Tree prev=state.getPath().getLeaf();
  for (  Tree curr : state.getPath().getParentPath()) {
    createFindIdentifiersScanner(usedSymbols,prev).scan(curr,null);
switch (curr.getKind()) {
case BLOCK:
      for (      StatementTree statement : ((BlockTree)curr).getStatements()) {
        if (statement.equals(prev)) {
          break;
        }
        addIfVariable(statement,definedVariables);
      }
    break;
case FOR_LOOP:
  ForLoopTree forLoop=(ForLoopTree)curr;
forLoop.getInitializer().stream().forEach(t -> addIfVariable(t,definedVariables));
break;
case ENHANCED_FOR_LOOP:
EnhancedForLoopTree enhancedFor=(EnhancedForLoopTree)curr;
addIfVariable(enhancedFor.getVariable(),definedVariables);
break;
default :
break;
}
prev=curr;
}
return ImmutableSet.copyOf(Sets.difference(definedVariables.build(),usedSymbols.build()));
}
