@Override public synchronized void write(byte[] bytes) throws IOException {
  buf.put(bytes);
}
