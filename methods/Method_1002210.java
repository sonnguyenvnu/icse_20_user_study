@Override public InputStream build(){
  ensureRequiredFieldsAreSet();
  writePrologue();
  writeMediaTags();
  writeEpilogue();
  return FileHelpers.toStream(mWriter.toString());
}
