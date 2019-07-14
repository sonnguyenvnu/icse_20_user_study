public static SourceFile create(JavaFileObject fileObject) throws IOException {
  return new SourceFile(fileObject.toUri().getPath(),fileObject.getCharContent(false));
}
