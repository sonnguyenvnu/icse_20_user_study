public File toFile() throws URISyntaxException {
  return url != null ? new File(url.toURI()) : null;
}
