private static long computeFileChecksum(final File sourceFile){
  try (CheckedInputStream stream=new CheckedInputStream(new BufferedInputStream(Files.newInputStream(sourceFile.toPath())),new Adler32())){
    IOUtils.skipFully(stream,sourceFile.length());
    return stream.getChecksum().getValue();
  }
 catch (  final IOException ignored) {
  }
  return 0;
}
