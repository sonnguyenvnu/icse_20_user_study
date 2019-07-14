private Resource asResource(final HttpRequest httpRequest){
  return text(targetFile(httpRequest).getPath());
}
