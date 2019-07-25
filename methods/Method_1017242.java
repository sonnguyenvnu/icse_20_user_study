@Override public MetadataBackend decorate(final MetadataBackend backend){
  return new InstrumentedMetadataBackend(backend);
}
