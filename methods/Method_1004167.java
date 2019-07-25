public List<String> initialize(){
  explicitScopes=getExplicitScopes();
  allScopes.putAll(explicitScopes);
  allScopes.putAll(getEitherComponentScopes());
  allScopes.putAll(getEitherComponentBuilderScopes());
  allScopes.putAll(getComponentDependenciesScopes());
  allScopes.putAll(getBindsInstanceScopes());
  dumpAllScopes(TAG + " all scopes before calc: ");
  for (  BindingKey key : bindingsRequired) {
    if (!allScopes.containsKey(key)) {
      calculateInternal(key,Lists.<BindingKey>newArrayList());
    }
  }
  dumpAllScopes(TAG + " all scopes after calc: ");
  if (!allScopes.keySet().containsAll(bindingsRequired)) {
    errors.add(String.format("Scope of required keys not calculated.\nDiff: %s\nRequired: %s\nCalculated: %s",Sets.difference(bindingsRequired,allScopes.keySet()),bindingsRequired,allScopes));
  }
  verifyScopes();
  if (errors.isEmpty()) {
    initialized=true;
  }
  return errors;
}
