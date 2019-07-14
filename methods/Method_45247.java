private File targetFile(final HttpRequest request){
  Optional<String> relativePath=extractor.extract(request);
  if (!relativePath.isPresent()) {
    throw new IllegalStateException("Reach mount handler without relative path");
  }
  return new File(dir,relativePath.get());
}
