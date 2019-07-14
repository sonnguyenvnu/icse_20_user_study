private void configureMetricsSlf4jReporter(){
  if (configuration.has(METRICS_SLF4J_INTERVAL)) {
    MetricManager.INSTANCE.addSlf4jReporter(configuration.get(METRICS_SLF4J_INTERVAL),configuration.has(METRICS_SLF4J_LOGGER) ? configuration.get(METRICS_SLF4J_LOGGER) : null);
  }
}
