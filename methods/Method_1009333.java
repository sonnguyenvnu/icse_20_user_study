public <V>void skip(DestinationSetter<D,V> destinationSetter){
  notNull(destinationSetter,"destinationSetter");
  new ReferenceMapExpressionImpl<S,D>(typeMap,options).skip(destinationSetter);
  options=new MappingOptions();
}
