private URL[] getClassPathEntries(){
  final String classpath=System.getProperty("java.class.path");
  final String[] classpathEntries=classpath.split(File.pathSeparator);
  final List<URL> entries=new ArrayList<>();
  final SimpleFileVisitor<Path> fileVisitor=new SimpleFileVisitor<Path>(){
    @Override public FileVisitResult visitFile(    final Path file,    final BasicFileAttributes attrs) throws IOException {
      if (!attrs.isSymbolicLink()) {
        entries.add(file.toUri().toURL());
      }
      return FileVisitResult.CONTINUE;
    }
  }
;
  final SimpleFileVisitor<Path> jarFileVisitor=new SimpleFileVisitor<Path>(){
    @Override public FileVisitResult visitFile(    final Path file,    final BasicFileAttributes attrs) throws IOException {
      String extension=FilenameUtils.getExtension(file.toString());
      if ("jar".equalsIgnoreCase(extension)) {
        fileVisitor.visitFile(file,attrs);
      }
      return FileVisitResult.CONTINUE;
    }
  }
;
  try {
    for (    final String entry : classpathEntries) {
      final File f=new File(entry);
      if (isClassPathWildcard(entry)) {
        Files.walkFileTree(new File(entry.substring(0,entry.length() - 1)).toPath(),EnumSet.of(FileVisitOption.FOLLOW_LINKS),1,jarFileVisitor);
      }
 else       if (f.isFile()) {
        entries.add(f.toURI().toURL());
      }
 else {
        Files.walkFileTree(f.toPath(),EnumSet.of(FileVisitOption.FOLLOW_LINKS),Integer.MAX_VALUE,fileVisitor);
      }
    }
  }
 catch (  final IOException e) {
    LOG.log(Level.SEVERE,"Incremental analysis can't check execution classpath contents",e);
    throw new RuntimeException(e);
  }
  return entries.toArray(new URL[0]);
}
