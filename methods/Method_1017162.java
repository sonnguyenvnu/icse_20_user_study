private InputStream open(ShellIO io,Path file) throws IOException {
  final InputStream input=io.newInputStream(file);
  if (!file.getFileName().toString().endsWith(".gz")) {
    return input;
  }
  return new GZIPInputStream(input);
}
