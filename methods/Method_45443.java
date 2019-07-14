public Optional<MocoConfig> context(){
  if (context != null) {
    return of(Moco.context(context));
  }
  return absent();
}
