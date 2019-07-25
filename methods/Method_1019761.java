public void save(Writer writer) throws IOException {
  writer.write(getCurrentBuffer().toString());
}
