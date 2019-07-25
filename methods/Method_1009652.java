@Override public void close(){
  if (influxDBClient.isBatchEnabled()) {
    influxDBClient.disableBatch();
  }
  influxDBClient.close();
}
