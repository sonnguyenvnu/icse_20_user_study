@Override public void write(PrintWriter writer) throws IOException {
  if (!isEmpty()) {
    writer.println("# " + this.title);
    writer.println("");
    for (    Section section : resolveSubSections(this.subSections)) {
      section.write(writer);
    }
  }
}
