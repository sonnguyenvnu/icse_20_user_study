public double missRate(){
  long requestCount=requestCount();
  return (requestCount == 0) ? 0.0 : (double)missCount / requestCount;
}
