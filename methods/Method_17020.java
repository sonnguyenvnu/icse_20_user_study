@Benchmark public long striped64(){
  int hash=getProbe();
  if (hash == 0) {
    ThreadLocalRandom.current();
    hash=getProbe();
  }
  advanceProbe(hash);
  int index=selectSlot(hash);
  return array[index];
}
