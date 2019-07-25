public static SuggestKey setup(final CoreComponent core){
  return DaggerSuggestKey_C.builder().coreComponent(core).build().task();
}
