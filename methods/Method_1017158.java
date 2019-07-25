public static Pause setup(final CoreComponent core){
  return DaggerPause_C.builder().coreComponent(core).build().task();
}
