@Override public void doReport(SofaTracerSpan span){
  lock.lock();
  if (span.isClient()) {
    try {
      String result=clientDigestEncoder.encode(span);
      clientDigestHolder.add(result);
    }
 catch (    IOException e) {
      LOGGER.error("encode error",e);
    }
  }
 else {
    try {
      String result=serverDigestEncoder.encode(span);
      serverDigestHolder.add(result);
    }
 catch (    IOException e) {
      LOGGER.error("encode error",e);
    }
  }
  if (statReporter != null) {
    statisticReport(span);
  }
  lock.unlock();
}
