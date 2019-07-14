private TypeSpec makeLocalCacheSpec(String className,boolean isFinal,Set<Feature> features){
  TypeName superClass;
  Set<Feature> parentFeatures;
  Set<Feature> generateFeatures;
  if (features.size() == 2) {
    parentFeatures=ImmutableSet.of();
    generateFeatures=features;
    superClass=BOUNDED_LOCAL_CACHE;
  }
 else {
    parentFeatures=ImmutableSet.copyOf(Iterables.limit(features,features.size() - 1));
    generateFeatures=ImmutableSet.of(Iterables.getLast(features));
    superClass=ParameterizedTypeName.get(ClassName.bestGuess(encode(Feature.makeClassName(parentFeatures))),kTypeVar,vTypeVar);
  }
  LocalCacheContext context=new LocalCacheContext(superClass,className,isFinal,parentFeatures,generateFeatures);
  for (  LocalCacheRule rule : rules) {
    rule.accept(context);
  }
  return context.cache.build();
}
