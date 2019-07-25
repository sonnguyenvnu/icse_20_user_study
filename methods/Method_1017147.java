public static MetadataCount setup(final CoreComponent core){
  return DaggerMetadataCount_C.builder().coreComponent(core).build().task();
}
