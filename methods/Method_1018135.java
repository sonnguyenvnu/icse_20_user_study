@Nonnull @Override public Optional<File> locate(@Nonnull final String filename){
  return Optional.ofNullable(System.getenv("KYLO_SERVICES_HOME")).map(servicesHome -> new StringJoiner(File.separator).add(servicesHome).add("lib").add("app").add(filename).toString()).map(File::new);
}
