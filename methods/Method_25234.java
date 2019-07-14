private static ImmutableSet<InferenceVariable> findUnannotatedTypeVarRefs(TypeVariableSymbol typeVar,Type declaredType,@Nullable Symbol decl,Tree sourceNode){
  ImmutableSet.Builder<InferenceVariable> result=ImmutableSet.builder();
  visitTypeVarRefs(typeVar,declaredType,new ArrayDeque<>(),null,(typeVarRef,selector,unused) -> {
    if (!extractExplicitNullness(typeVarRef,selector.isEmpty() ? decl : null).isPresent()) {
      result.add(TypeArgInferenceVar.create(ImmutableList.copyOf(selector),sourceNode));
    }
  }
);
  return result.build();
}
