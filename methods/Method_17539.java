public double hitRate(){
  long requestCount=requestCount();
  return (requestCount == 0) ? 1.0 : (double)hitCount / requestCount;
}
