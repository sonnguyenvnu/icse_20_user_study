private void configureMetricsCsvReporter(){
  if (configuration.has(METRICS_CSV_DIR)) {
    MetricManager.INSTANCE.addCsvReporter(configuration.get(METRICS_CSV_INTERVAL),configuration.get(METRICS_CSV_DIR));
  }
}
