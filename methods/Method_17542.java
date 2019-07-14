public double complexity(){
  long requestCount=requestCount();
  return (requestCount == 0) ? 0.0 : (double)operationCount / requestCount;
}
