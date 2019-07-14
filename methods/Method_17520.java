@Override public void record(long key){
  tick++;
  future.enqueue(key);
  IntPriorityQueue times=accessTimes.get(key);
  if (times == null) {
    times=new IntArrayFIFOQueue();
    accessTimes.put(key,times);
  }
  times.enqueue(tick);
}
