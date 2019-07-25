public static DataMigrate setup(final CoreComponent core){
  return DaggerDataMigrate_C.builder().coreComponent(core).build().task();
}
