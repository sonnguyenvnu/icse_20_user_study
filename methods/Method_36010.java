public RequestPatternBuilder withHeader(String key,StringValuePattern valuePattern){
  headers.put(key,MultiValuePattern.of(valuePattern));
  return this;
}
