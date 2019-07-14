public MultiValuePattern asAuthorizationMultiValuePattern(){
  return MultiValuePattern.of(equalToIgnoreCase(asAuthorizationHeaderValue()));
}
