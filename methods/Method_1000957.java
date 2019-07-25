public SequenceWriter init(boolean wrapInArray) throws IOException {
  if (wrapInArray) {
    _generator.writeStartArray();
    _openArray=true;
  }
  return this;
}
