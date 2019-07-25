/** 
 * Returns the biggest scope that can be applied to the given {@link BindingKey}. Crash if no scope is applicable, which means a bug in code being processed.
 */
public TypeElement calculate(BindingKey key){
  Preconditions.checkState(initialized,"ScopeCalculator is not initialized yet.");
  ScopeCalculatingInfo info=allScopes.get(key);
  Preconditions.checkNotNull(info,"Did not find scope info for %s",key);
  return info.scope;
}
