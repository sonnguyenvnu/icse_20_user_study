@Override public D map(S source){
  Class<S> sourceType=Types.deProxy(source.getClass());
  MappingContextImpl<S,D> context=new MappingContextImpl<S,D>(source,sourceType,null,destinationType,null,name,engine);
  D result=null;
  try {
    result=engine.typeMap(context,this);
  }
 catch (  Throwable t) {
    context.errors.errorMapping(sourceType,destinationType,t);
  }
  context.errors.throwMappingExceptionIfErrorsExist();
  return result;
}
