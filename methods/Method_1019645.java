@Override public void save(Writer writer) throws IOException {
  if (before != null) {
    writer.write(before.toString());
  }
  super.save(writer);
  if (after != null) {
    writer.write(after.toString());
  }
}
