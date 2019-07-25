/** 
 * This method creates a gzip tarball of the specified directory. File permissions will be retained. The file will be created in a temporary directory using the  {@link Files#createTempFile(String,String,java.nio.file.attribute.FileAttribute[])} method. Thereturned object is auto-closeable, and upon closing it, the archive file will be deleted.
 * @param directory the directory to compress
 * @return a Path object representing the compressed directory
 * @throws IOException if the compressed directory could not be created.
 */
public static CompressedDirectory create(final Path directory) throws IOException {
  final Path file=Files.createTempFile("docker-client-",".tar.gz");
  final Path dockerIgnorePath=directory.resolve(".dockerignore");
  final ImmutableList<DockerIgnorePathMatcher> ignoreMatchers=parseDockerIgnore(dockerIgnorePath);
  try (final OutputStream fileOut=Files.newOutputStream(file);final GzipCompressorOutputStream gzipOut=new GzipCompressorOutputStream(fileOut);final TarArchiveOutputStream tarOut=new TarArchiveOutputStream(gzipOut)){
    tarOut.setLongFileMode(LONGFILE_POSIX);
    tarOut.setBigNumberMode(BIGNUMBER_POSIX);
    Files.walkFileTree(directory,EnumSet.of(FileVisitOption.FOLLOW_LINKS),Integer.MAX_VALUE,new Visitor(directory,ignoreMatchers,tarOut));
  }
 catch (  Throwable t) {
    try {
      Files.delete(file);
    }
 catch (    IOException e) {
      t.addSuppressed(e);
    }
    throw t;
  }
  return new CompressedDirectory(file);
}
