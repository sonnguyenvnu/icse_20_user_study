private TypeSpec makeNodeSpec(String className,boolean isFinal,Set<Feature> features){
  TypeName superClass;
  Set<Feature> parentFeatures;
  Set<Feature> generateFeatures;
  if (features.size() == 2) {
    parentFeatures=ImmutableSet.of();
    generateFeatures=features;
    superClass=TypeName.OBJECT;
  }
 else {
    parentFeatures=ImmutableSet.copyOf(Iterables.limit(features,features.size() - 1));
    generateFeatures=ImmutableSet.of(Iterables.getLast(features));
    superClass=ParameterizedTypeName.get(ClassName.get(PACKAGE_NAME,encode(Feature.makeClassName(parentFeatures))),kTypeVar,vTypeVar);
  }
  NodeContext context=new NodeContext(superClass,className,isFinal,parentFeatures,generateFeatures);
  for (  NodeRule rule : rules) {
    rule.accept(context);
  }
  return context.nodeSubtype.build();
}
