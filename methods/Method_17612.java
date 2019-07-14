public void record(long key){
  int hint=sketch.frequency(key);
  hinter.increment(hint);
  sketch.increment(key);
  estSkew.record(key);
  sample++;
}
