@Nonnull @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops") public RealStoreBuilder<Raw,Parsed,Key> parsers(final @Nonnull List<Parser> parsers){
  this.parsers.clear();
  for (  Parser parser : parsers) {
    this.parsers.add(new NoKeyParser<>(parser));
  }
  return this;
}
