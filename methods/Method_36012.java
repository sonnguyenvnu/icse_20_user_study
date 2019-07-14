public RequestPatternBuilder withCookie(String key,StringValuePattern valuePattern){
  cookies.put(key,valuePattern);
  return this;
}
