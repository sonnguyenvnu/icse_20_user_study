public static MetadataDelete setup(final CoreComponent core){
  return DaggerMetadataDelete_C.builder().coreComponent(core).build().task();
}
