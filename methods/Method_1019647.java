@Override public void save(Writer writer) throws IOException {
  writer.write(buffer.toString());
}
