@Override public long sampleMillis(){
  return ThreadLocalRandom.current().nextLong(lower,upper + 1);
}
