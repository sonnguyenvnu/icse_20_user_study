@Override public ApolloPrefetch clone(){
  return new RealApolloPrefetch(operation,serverUrl,httpCallFactory,scalarTypeAdapters,dispatcher,logger,tracker);
}
