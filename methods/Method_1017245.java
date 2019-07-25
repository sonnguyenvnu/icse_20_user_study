@Override public SuggestBackend decorate(final SuggestBackend backend){
  return new InstrumentedSuggestBackend(backend);
}
