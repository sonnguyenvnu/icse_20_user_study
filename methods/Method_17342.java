private static float average(long requestCount,long opsTimeNanos){
  if ((requestCount == 0) || (opsTimeNanos == 0)) {
    return 0;
  }
  long opsTimeMicro=TimeUnit.NANOSECONDS.toMicros(opsTimeNanos);
  return (float)opsTimeMicro / requestCount;
}
