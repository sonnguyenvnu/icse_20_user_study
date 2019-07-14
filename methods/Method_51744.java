public List<String> check() throws IOException {
  Files.walkFileTree(pagesDirectory,new SimpleFileVisitor<Path>(){
    @Override public FileVisitResult visitFile(    Path file,    BasicFileAttributes attrs) throws IOException {
      checkFile(file);
      return super.visitFile(file,attrs);
    }
  }
);
  return issues;
}
