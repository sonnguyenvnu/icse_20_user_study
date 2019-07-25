@Override public String content(Charset charset) throws IOException {
  return new String(Files.readAllBytes(path),charset);
}
