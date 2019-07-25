static long base(long timestamp){
  return timestamp - timestamp % PERIOD;
}
