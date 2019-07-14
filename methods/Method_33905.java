public InputStream loadSparklrPhoto(String id) throws SparklrException {
  return new ByteArrayInputStream(sparklrRestTemplate.getForObject(URI.create(String.format(sparklrPhotoURLPattern,id)),byte[].class));
}
