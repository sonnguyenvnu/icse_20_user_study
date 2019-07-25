@Nonnull @Override public Optional<File> locate(@Nonnull final String filename){
  return Optional.ofNullable(getClass().getProtectionDomain().getCodeSource()).map(CodeSource::getLocation).map(url -> {
    try {
      return url.toURI();
    }
 catch (    final URISyntaxException e) {
      return null;
    }
  }
).map(URI::getPath).map(File::new).map(File::getParentFile).map(parent -> new File(parent,"app" + File.separator + filename));
}
