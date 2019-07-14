@Override public long sampleMillis(){
  return Math.round(Math.exp(ThreadLocalRandom.current().nextGaussian() * sigma) * median);
}
