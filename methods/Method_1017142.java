public static CountData setup(final CoreComponent core){
  return DaggerCountData_C.builder().coreComponent(core).build().task();
}
