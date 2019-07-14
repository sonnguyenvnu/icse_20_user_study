public static long randomDelay(long delay,double jitterFactor,double random){
  double randomFactor=1 + (1 - random * 2) * jitterFactor;
  return (long)(delay * randomFactor);
}
