/** 
 * Records which arguments are guaranteed to be non-null if the method completes without exception. For example, if  {@code checkNotNull(foo, message)} completes successfully, then{@code foo} is not null.
 */
private static void setUnconditionalArgumentNullness(Updates bothUpdates,List<Node> arguments,ClassAndMethod callee){
  Set<Integer> requiredNonNullParameters=REQUIRED_NON_NULL_PARAMETERS.get(callee.name());
  for (  LocalVariableNode var : variablesAtIndexes(requiredNonNullParameters,arguments)) {
    bothUpdates.set(var,NONNULL);
  }
}
