public static SuggestTagValue setup(final CoreComponent core){
  return DaggerSuggestTagValue_C.builder().coreComponent(core).build().task();
}
