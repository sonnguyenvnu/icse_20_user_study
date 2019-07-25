public static MetadataFindSeriesIds setup(final CoreComponent core){
  return DaggerMetadataFindSeriesIds_C.builder().coreComponent(core).build().task();
}
