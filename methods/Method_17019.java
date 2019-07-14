@Benchmark public int threadHashCode(){
  long id=Thread.currentThread().hashCode();
  int hash=(((int)(id ^ (id >>> 32))) ^ 0x811c9dc5) * 0x01000193;
  return selectSlot(hash);
}
