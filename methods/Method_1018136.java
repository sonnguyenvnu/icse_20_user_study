@Nonnull @Override public Optional<File> locate(){
  return Optional.ofNullable(System.getenv("SPARK_HOME")).map(File::new);
}
