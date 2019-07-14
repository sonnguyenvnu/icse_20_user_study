@Override public boolean nextKeyValue() throws IOException, InterruptedException {
  return null != (currentKV=completeNextKV());
}
