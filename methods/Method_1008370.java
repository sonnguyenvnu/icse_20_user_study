@Override public void collect(int doc) throws IOException {
  if (scorer.score() >= minimumScore) {
    leafCollector.collect(doc);
  }
}
