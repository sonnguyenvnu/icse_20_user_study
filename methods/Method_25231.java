private void generateConstraintsFromAnnotations(Type inferredType,@Nullable Symbol decl,@Nullable Type declaredType,Tree sourceTree,ArrayDeque<Integer> argSelector){
  List<Type> inferredTypeArguments=inferredType.getTypeArguments();
  List<Type> declaredTypeArguments=declaredType != null ? declaredType.getTypeArguments() : ImmutableList.of();
  int numberOfTypeArgs=inferredTypeArguments.size();
  for (int i=0; i < numberOfTypeArgs; i++) {
    argSelector.push(i);
    generateConstraintsFromAnnotations(inferredTypeArguments.get(i),decl,i < declaredTypeArguments.size() ? declaredTypeArguments.get(i) : null,sourceTree,argSelector);
    argSelector.pop();
  }
  Optional<Nullness> fromAnnotations=extractExplicitNullness(declaredType,argSelector.isEmpty() ? decl : null);
  if (!fromAnnotations.isPresent()) {
    fromAnnotations=NullnessAnnotations.fromAnnotationsOn(inferredType);
  }
  if (!fromAnnotations.isPresent()) {
    if (declaredType instanceof TypeVariable) {
      fromAnnotations=NullnessAnnotations.getUpperBound((TypeVariable)declaredType);
    }
 else {
      fromAnnotations=NullnessAnnotations.fromDefaultAnnotations(decl);
    }
  }
  fromAnnotations.map(ProperInferenceVar::create).ifPresent(annot -> {
    InferenceVariable var=TypeArgInferenceVar.create(ImmutableList.copyOf(argSelector),sourceTree);
    qualifierConstraints.putEdge(var,annot);
    qualifierConstraints.putEdge(annot,var);
  }
);
}
