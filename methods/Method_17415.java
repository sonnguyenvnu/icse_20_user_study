@Override @SuppressWarnings("PMD.CloseResource") public LongStream events() throws IOException {
  DataInputStream input=new DataInputStream(new BufferedInputStream(readFile()));
  LongStream stream=StreamSupport.longStream(Spliterators.spliteratorUnknownSize(new TraceIterator(input),Spliterator.ORDERED),false);
  return stream.onClose(() -> Closeables.closeQuietly(input));
}
