public void filter(DaoInterceptorChain chain) throws DaoException {
  Stopwatch sw=Stopwatch.begin();
  try {
    chain.doChain();
  }
  finally {
    sw.stop();
    if (log.isDebugEnabled())     log.debugf("time=%sms, sql=%s",sw.getDuration(),chain.getDaoStatement().toString());
  }
}
