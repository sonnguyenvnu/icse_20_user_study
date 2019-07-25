@NotNull public static ModelRootDescriptor load(@NotNull ModelInputStream stream) throws IOException {
  if (stream.readByte() != MODEL_ROOT_START_MARKER) {
    throw new IOException("bad stream: no model root descriptor start marker");
  }
  return new ModelRootDescriptor(stream.readString(),MementoStreamUtil.readMemento(null,stream));
}
