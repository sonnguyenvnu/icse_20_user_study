private void visitTypeComponents(Type type,ArrayDeque<Integer> partialSelector,Tree sourceNode,Consumer<TypeArgInferenceVar> consumer){
  List<Type> typeArguments=type.getTypeArguments();
  for (int i=0; i < typeArguments.size(); ++i) {
    partialSelector.push(i);
    visitTypeComponents(typeArguments.get(i),partialSelector,sourceNode,consumer);
    partialSelector.pop();
  }
  consumer.accept(TypeArgInferenceVar.create(ImmutableList.copyOf(partialSelector),sourceNode));
}
