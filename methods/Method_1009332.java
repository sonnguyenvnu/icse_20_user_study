public <V>void map(SourceGetter<S> sourceGetter,DestinationSetter<D,V> destinationSetter){
  notNull(sourceGetter,"sourceGetter");
  notNull(destinationSetter,"destinationSetter");
  new ReferenceMapExpressionImpl<S,D>(typeMap,options).map(sourceGetter,destinationSetter);
  options=new MappingOptions();
}
