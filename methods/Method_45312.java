private static ImmutableList<String> doGlob(final Path path,final Path searchPath){
  final PathMatcher matcher=FileSystems.getDefault().getPathMatcher("glob:" + path);
  try {
    final ImmutableList.Builder<String> builder=ImmutableList.builder();
    Files.walkFileTree(searchPath,new SimpleFileVisitor<Path>(){
      @Override public FileVisitResult visitFile(      final Path file,      final BasicFileAttributes attrs) throws IOException {
        if (matcher.matches(file)) {
          builder.add(file.toString());
        }
        return FileVisitResult.CONTINUE;
      }
    }
);
    return builder.build();
  }
 catch (  IOException e) {
    throw new MocoException(e);
  }
}
