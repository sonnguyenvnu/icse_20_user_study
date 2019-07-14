public static long randomDelay(long delay,long jitter,double random){
  double randomAddend=(1 - random * 2) * jitter;
  return (long)(delay + randomAddend);
}
