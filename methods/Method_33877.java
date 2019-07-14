public InputStream loadSparklrPhoto(String id) throws SparklrException {
  return new ByteArrayInputStream(getSparklrRestTemplate().getForObject(URI.create(String.format(getSparklrPhotoURLPattern(),id)),byte[].class));
}
