public Object convert(MappingContext<Object,Object> context){
  MappingContextImpl<Object,Object> contextImpl=(MappingContextImpl<Object,Object>)context;
  return (!contextImpl.isProvidedDestination() && context.getDestination() != null) ? context.getDestination() : context.getSource();
}
