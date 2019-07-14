@Nonnull public static <T>Persister<BufferedSource,T> create(FileSystem fileSystem,PathResolver<T> pathResolver){
  if (fileSystem == null) {
    throw new IllegalArgumentException("root file cannot be null.");
  }
  return new FileSystemPersister<>(fileSystem,pathResolver);
}
