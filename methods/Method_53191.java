private void checkNotBuilt(){
  if (configurationBean == null) {
    throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
  }
}
