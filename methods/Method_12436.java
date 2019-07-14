private URI createUrl(){
  if (url == null) {
    throw new IllegalStateException("'url' must not be null.");
  }
  return URI.create(String.format("%s/rooms/%s/messages",url,room));
}
