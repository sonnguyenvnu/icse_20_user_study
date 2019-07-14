/** 
 * Get inferred nullness qualifiers for method-generic type variables at a callsite. When inference is not possible for a given type variable, that type variable is not included in the resulting map.
 */
public ImmutableMap<TypeVariableSymbol,Nullness> getNullnessGenerics(MethodInvocationTree callsite){
  ImmutableMap.Builder<TypeVariableSymbol,Nullness> result=ImmutableMap.builder();
  for (  TypeVariableSymbol tvs : TreeInfo.symbol((JCTree)callsite.getMethodSelect()).getTypeParameters()) {
    InferenceVariable iv=TypeVariableInferenceVar.create(tvs,callsite);
    if (constraintGraph.nodes().contains(iv)) {
      getNullness(iv).ifPresent(nullness -> result.put(tvs,nullness));
    }
  }
  return result.build();
}
