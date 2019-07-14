public Optional<MocoConfig> request(){
  if (request != null) {
    return of(Moco.request(request.getRequestMatcher()));
  }
  return absent();
}
