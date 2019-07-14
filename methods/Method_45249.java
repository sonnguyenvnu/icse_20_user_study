public Optional<String> extract(final String uri){
  if (uri.startsWith(this.target) && uri.length() != this.target.length()) {
    return fromNullable(uri.replaceFirst(this.target,""));
  }
  return absent();
}
