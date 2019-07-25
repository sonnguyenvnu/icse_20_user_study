public static MetadataTags setup(final CoreComponent core){
  return DaggerMetadataTags_C.builder().coreComponent(core).build().task();
}
