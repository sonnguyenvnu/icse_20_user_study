@Override public void close() throws IOException {
  if (generator.isClosed()) {
    return;
  }
  JsonStreamContext context=generator.getOutputContext();
  if ((context != null) && (context.inRoot() == false)) {
    throw new IOException("Unclosed object or array found");
  }
  if (writeLineFeedAtEnd) {
    flush();
    getLowLevelGenerator().writeRaw(LF);
  }
  generator.close();
}
