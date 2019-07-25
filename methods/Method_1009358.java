@Override public <P>TypeMap<S,D> include(TypeSafeSourceGetter<S,P> sourceGetter,Class<P> propertyType){
  @SuppressWarnings("unchecked") TypeMapImpl<? super S,? super D> childTypeMap=(TypeMapImpl<? super S,? super D>)configuration.typeMapStore.get(propertyType,destinationType,name);
  Assert.notNull(childTypeMap,"Cannot find child TypeMap");
  List<Accessor> accessors=PropertyReferenceCollector.collect(this,sourceGetter);
  for (  Mapping mapping : childTypeMap.getMappings()) {
    InternalMapping internalMapping=(InternalMapping)mapping;
    addMapping(internalMapping.createMergedCopy(accessors,Collections.<PropertyInfo>emptyList()));
  }
  return this;
}
