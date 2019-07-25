public static MetadataWrite setup(final CoreComponent core){
  return DaggerMetadataWrite_C.builder().coreComponent(core).build().task();
}
