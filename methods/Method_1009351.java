/** 
 * Performs mapping using a TypeMap if one exists, else a converter if one applies, else a newly created TypeMap. Recursive entry point.
 */
@Override @SuppressWarnings("unchecked") public <S,D>D map(MappingContext<S,D> context){
  MappingContextImpl<S,D> contextImpl=(MappingContextImpl<S,D>)context;
  Class<D> destinationType=context.getDestinationType();
  if (!Iterables.isIterable(destinationType)) {
    D circularDest=contextImpl.destinationForSource();
    if (circularDest != null && circularDest.getClass().isAssignableFrom(contextImpl.getDestinationType()))     return circularDest;
  }
  D destination=null;
  TypeMap<S,D> typeMap=typeMapStore.get(context.getSourceType(),context.getDestinationType(),context.getTypeMapName());
  if (typeMap != null) {
    destination=typeMap(contextImpl,typeMap);
  }
 else {
    Converter<S,D> converter=converterFor(context);
    if (converter != null && (context.getDestination() == null || context.getParent() != null))     destination=convert(context,converter);
 else     if (!Primitives.isPrimitive(context.getSourceType()) && !Primitives.isPrimitive(context.getDestinationType())) {
      typeMap=typeMapStore.getOrCreate(context.getSource(),context.getSourceType(),context.getDestinationType(),context.getTypeMapName(),this);
      destination=typeMap(contextImpl,typeMap);
    }
 else     if (context.getDestinationType().isAssignableFrom(context.getSourceType()))     destination=(D)context.getSource();
  }
  contextImpl.setDestination(destination,true);
  return destination;
}
