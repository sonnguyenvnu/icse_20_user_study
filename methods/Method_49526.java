@Override public Iterable<Entry> getCurrentValue() throws IOException, InterruptedException {
  return new HBaseMapIterable(reader.getCurrentValue().getMap().get(edgestoreFamilyBytes));
}
