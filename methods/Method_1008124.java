@Override public void collect(int doc) throws IOException {
  super.collect(doc);
  if (trackMaxScore) {
    maxScore=Math.max(maxScore,scorer.score());
  }
  totalHitCount++;
}
