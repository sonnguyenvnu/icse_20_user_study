public static ModuleFacetDescriptor load(ModelInputStream stream) throws IOException {
  if (stream.readByte() != START_MARKER) {
    throw new IOException("bad stream: no module facet descriptor start marker");
  }
  return new ModuleFacetDescriptor(stream.readString(),MementoStreamUtil.readMemento(null,stream));
}
