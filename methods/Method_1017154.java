private InputStreamReader open(ShellIO io,Path file) throws IOException {
  final InputStream input=io.newInputStream(file);
  if (!file.getFileName().toString().endsWith(".gz")) {
    return new InputStreamReader(input,Charsets.UTF_8);
  }
  return new InputStreamReader(new GZIPInputStream(input),Charsets.UTF_8);
}
