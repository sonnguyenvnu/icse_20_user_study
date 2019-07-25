public static Refresh setup(final CoreComponent core){
  return DaggerRefresh_C.builder().coreComponent(core).build().task();
}
