private void configureMetricsConsoleReporter(){
  if (configuration.has(METRICS_CONSOLE_INTERVAL)) {
    MetricManager.INSTANCE.addConsoleReporter(configuration.get(METRICS_CONSOLE_INTERVAL));
  }
}
