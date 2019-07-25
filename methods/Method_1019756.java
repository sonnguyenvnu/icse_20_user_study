@Override public void save() throws IOException {
  if (writer != null) {
    xhtml.save(writer);
  }
 else {
    xhtml.save(out);
  }
  super.save();
}
