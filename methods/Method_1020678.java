public T enable(final MapperFeature feature){
  modifiers.add(new MapperFeatureModifier(feature,true));
  return getBuilderInstance();
}
