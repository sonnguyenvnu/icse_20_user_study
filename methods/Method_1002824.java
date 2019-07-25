@Override public List<String> list(String path){
  path=normalizePath(path);
  try (Stream<Path> stream=Files.list(Paths.get(rootDir + path))){
    return stream.filter(Files::isReadable).map(p -> {
      final String fileName=p.getFileName().toString();
      return Files.isDirectory(p) ? fileName + '/' : fileName;
    }
).sorted(String.CASE_INSENSITIVE_ORDER).collect(toImmutableList());
  }
 catch (  IOException e) {
    return ImmutableList.of();
  }
}
