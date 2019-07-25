public static SuggestTagKeyCount setup(final CoreComponent core){
  return DaggerSuggestTagKeyCount_C.builder().coreComponent(core).build().task();
}
