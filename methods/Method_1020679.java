public T disable(final MapperFeature feature){
  modifiers.add(new MapperFeatureModifier(feature,false));
  return getBuilderInstance();
}
