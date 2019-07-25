@Override public void export(Collection<Metric> metrics){
  Session session=sender.createSession();
  try {
    for (    Metric metric : metrics) {
      for (      DataPoint datapoint : SignalFxSessionAdaptor.adapt(metric)) {
        session.setDatapoint(datapoint);
      }
    }
  }
  finally {
    try {
      session.close();
    }
 catch (    IOException e) {
      logger.log(Level.FINE,"Unable to close the session",e);
    }
  }
}
