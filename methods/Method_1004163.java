public List<String> initialize(){
  allScopes.putAll(getExplicitScopes());
  for (  NewBindingKey key : bindingsRequired) {
    if (!allScopes.containsKey(key)) {
      calculateInternal(key,Lists.<NewBindingKey>newArrayList());
    }
  }
  if (!allScopes.keySet().containsAll(bindingsRequired)) {
    errors.add(String.format("Scope of required keys not calculated.%nDiff: %s%nRequired: %s%nCalculated: %s",Sets.difference(bindingsRequired,allScopes.keySet()),bindingsRequired,allScopes));
  }
  verifyScopes();
  if (errors.isEmpty()) {
    initialized=true;
  }
  return errors;
}
