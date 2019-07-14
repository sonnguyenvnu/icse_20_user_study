private Optional<ContentResource> postContent(){
  if (content != null) {
    return of(content.asResource());
  }
  if (json != null) {
    return of(Moco.json(json));
  }
  return absent();
}
