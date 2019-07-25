@Override public <C>SpanContext extract(Format<C> format,C carrier){
  RegistryExtractorInjector<C> registryExtractor=TracerFormatRegistry.getRegistry(format);
  if (registryExtractor == null) {
    throw new IllegalArgumentException("Unsupported extractor format: " + format);
  }
  return registryExtractor.extract(carrier);
}
