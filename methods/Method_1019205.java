public static synchronized void init(HoodieWriteConfig metricConfig){
  if (initialized) {
    return;
  }
  try {
    metrics=new Metrics(metricConfig);
  }
 catch (  ConfigurationException e) {
    throw new HoodieException(e);
  }
  initialized=true;
}
