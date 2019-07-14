public Map<String,MultiValuePattern> combineBasicAuthAndOtherHeaders(){
  if (basicAuthCredentials == null) {
    return headers;
  }
  Map<String,MultiValuePattern> combinedHeaders=headers;
  ImmutableMap.Builder<String,MultiValuePattern> allHeadersBuilder=ImmutableMap.<String,MultiValuePattern>builder().putAll(firstNonNull(combinedHeaders,Collections.<String,MultiValuePattern>emptyMap()));
  allHeadersBuilder.put(AUTHORIZATION,basicAuthCredentials.asAuthorizationMultiValuePattern());
  combinedHeaders=allHeadersBuilder.build();
  return combinedHeaders;
}
