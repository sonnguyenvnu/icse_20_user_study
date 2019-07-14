/** 
 * Records which arguments are guaranteed to be non-null only if the method completes by returning {@code true} or only if the method completes by returning {@code false}. For example, if  {@code Strings.isNullOrEmpty(s)} returns {@code false}, then  {@code s} is not null.
 */
private static void setConditionalArgumentNullness(Updates thenUpdates,Updates elseUpdates,List<Node> arguments,ClassAndMethod callee,Types types,Symtab symtab){
  MemberName calleeName=callee.name();
  for (  LocalVariableNode var : variablesAtIndexes(NULL_IMPLIES_TRUE_PARAMETERS.get(calleeName),arguments)) {
    elseUpdates.set(var,NONNULL);
  }
  for (  LocalVariableNode var : variablesAtIndexes(NONNULL_IFF_TRUE_PARAMETERS.get(calleeName),arguments)) {
    thenUpdates.set(var,NONNULL);
    elseUpdates.set(var,NULL);
  }
  for (  LocalVariableNode var : variablesAtIndexes(NULL_IFF_TRUE_PARAMETERS.get(calleeName),arguments)) {
    thenUpdates.set(var,NULL);
    elseUpdates.set(var,NONNULL);
  }
  if (isEqualsMethod(calleeName,arguments,types,symtab)) {
    LocalVariableNode var=variablesAtIndexes(ImmutableSet.of(0),arguments).get(0);
    thenUpdates.set(var,NONNULL);
  }
}
