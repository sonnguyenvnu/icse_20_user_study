@Nonnull @Override public T load(){
  Preconditions.checkNotNull(options.getFormat(),"Format must be defined");
  final Option<DataSetProvider<T>> provider=client.getDataSetProvider(options.getFormat());
  if (!provider.isDefined()) {
    throw new KyloCatalogException("Format is not supported: " + options.getFormat());
  }
  try {
    return resourceLoader.runWithThreadContext(new Callable<T>(){
      @Override public T call(){
        return provider.get().read(client,options);
      }
    }
);
  }
 catch (  final Exception e) {
    throw new KyloCatalogException("Unable to load '" + options.getFormat() + "' source: " + e,e);
  }
}
