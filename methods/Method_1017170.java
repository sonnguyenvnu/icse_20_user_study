public static WritePerformance setup(final CoreComponent core){
  return DaggerWritePerformance_C.builder().coreComponent(core).build().task();
}
