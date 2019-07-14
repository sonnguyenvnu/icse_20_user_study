private void generateConstraintsForWrite(Type lType,@Nullable Symbol decl,ExpressionTree rVal,@Nullable Tree lVal,ArrayDeque<Integer> argSelector){
  List<Type> typeArguments=lType.getTypeArguments();
  for (int i=0; i < typeArguments.size(); i++) {
    argSelector.push(i);
    generateConstraintsForWrite(typeArguments.get(i),decl,rVal,lVal,argSelector);
    argSelector.pop();
  }
  ImmutableList<Integer> argSelectorList=ImmutableList.copyOf(argSelector);
  boolean isBound=false;
  Optional<Nullness> fromAnnotations=extractExplicitNullness(lType,argSelector.isEmpty() ? decl : null);
  if (!fromAnnotations.isPresent()) {
    if (lType instanceof TypeVariable) {
      fromAnnotations=NullnessAnnotations.getUpperBound((TypeVariable)lType);
      isBound=true;
    }
 else {
      fromAnnotations=NullnessAnnotations.fromDefaultAnnotations(decl);
    }
  }
  boolean oneSided=isBound || argSelector.isEmpty();
  fromAnnotations.map(ProperInferenceVar::create).ifPresent(annot -> {
    InferenceVariable var=TypeArgInferenceVar.create(argSelectorList,rVal);
    qualifierConstraints.putEdge(var,annot);
    if (!oneSided) {
      qualifierConstraints.putEdge(annot,var);
    }
  }
);
  if (lVal != null) {
    qualifierConstraints.putEdge(TypeArgInferenceVar.create(argSelectorList,rVal),TypeArgInferenceVar.create(argSelectorList,lVal));
  }
}
