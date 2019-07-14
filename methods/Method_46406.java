public Date readFrom(Input input) throws IOException {
  return new Date(input.readFixed64());
}
