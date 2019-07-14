/** 
 * Spins forever writing into the cache. 
 */
private void writes(){
  int index=random.nextInt();
  for (; ; ) {
    Integer key=ints[index++ & MASK];
    cache.put(key,Boolean.TRUE);
    calls.increment();
  }
}
