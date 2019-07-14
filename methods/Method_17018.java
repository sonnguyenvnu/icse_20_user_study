@Benchmark public int threadIdHash(){
  long id=Thread.currentThread().getId();
  int hash=(((int)(id ^ (id >>> 32))) ^ 0x811c9dc5) * 0x01000193;
  return selectSlot(hash);
}
