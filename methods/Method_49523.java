@Override public boolean nextKeyValue() throws IOException {
  return null != (currentKV=completeNextKV());
}
