public synchronized Enumerator elements() throws IOException {
  return new TLCTraceEnumerator();
}
