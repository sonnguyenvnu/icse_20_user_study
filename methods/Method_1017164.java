public static SuggestTag setup(final CoreComponent core){
  return DaggerSuggestTag_C.builder().coreComponent(core).build().task();
}
