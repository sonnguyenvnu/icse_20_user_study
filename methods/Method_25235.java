private void visitUnannotatedTypeVarRefsAndEquateInferredComponents(TypeVariableInferenceVar typeVar,Type type,@Nullable Symbol decl,Tree sourceNode,Consumer<TypeArgInferenceVar> consumer){
  visitTypeVarRefs(typeVar.typeVar(),type,new ArrayDeque<>(),((JCExpression)sourceNode).type,(declaredType,selector,inferredType) -> {
    if (!extractExplicitNullness(type,selector.isEmpty() ? decl : null).isPresent()) {
      consumer.accept(TypeArgInferenceVar.create(ImmutableList.copyOf(selector),sourceNode));
    }
    if (inferredType == null) {
      return;
    }
    List<Type> typeArguments=inferredType.getTypeArguments();
    int depth=selector.size();
    for (int i=0; i < typeArguments.size(); ++i) {
      selector.push(i);
      visitTypeComponents(typeArguments.get(i),selector,sourceNode,typeArg -> {
        TypeVariableInferenceVar typeVarComponent=typeVar.withSelector(typeArg.typeArgSelector().subList(depth,typeArg.typeArgSelector().size()));
        qualifierConstraints.putEdge(typeVarComponent,typeArg);
        qualifierConstraints.putEdge(typeArg,typeVarComponent);
      }
);
      selector.pop();
    }
  }
);
}
