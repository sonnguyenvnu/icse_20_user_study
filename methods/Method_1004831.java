private void setup(MaxwellConfig config){
  metricsPrefix=config.metricsPrefix;
  if (config.metricsReportingType == null) {
    LOGGER.warn("Metrics will not be exposed: metricsReportingType not configured.");
    return;
  }
  if (config.metricsJvm) {
    config.metricRegistry.register(metricName("jvm","memory_usage"),new MemoryUsageGaugeSet());
    config.metricRegistry.register(metricName("jvm","gc"),new GarbageCollectorMetricSet());
    config.metricRegistry.register(metricName("jvm","class_loading"),new ClassLoadingGaugeSet());
    config.metricRegistry.register(metricName("jvm","file_descriptor_ratio"),new FileDescriptorRatioGauge());
    config.metricRegistry.register(metricName("jvm","thread_states"),new CachedThreadStatesGaugeSet(60,TimeUnit.SECONDS));
  }
  if (config.metricsReportingType.contains(reportingTypeSlf4j)) {
    final Slf4jReporter reporter=Slf4jReporter.forRegistry(config.metricRegistry).outputTo(LOGGER).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
    reporter.start(config.metricsSlf4jInterval,TimeUnit.SECONDS);
    LOGGER.info("Slf4j metrics reporter enabled");
  }
  if (config.metricsReportingType.contains(reportingTypeJmx)) {
    final JmxReporter jmxReporter=JmxReporter.forRegistry(config.metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
    jmxReporter.start();
    LOGGER.info("JMX metrics reporter enabled");
    if (System.getProperty("com.sun.management.jmxremote") == null) {
      LOGGER.warn("JMX remote is disabled");
    }
 else {
      String portString=System.getProperty("com.sun.management.jmxremote.port");
      if (portString != null) {
        LOGGER.info("JMX running on port " + Integer.parseInt(portString));
      }
    }
  }
  if (config.metricsReportingType.contains(reportingTypeDataDog)) {
    Transport transport;
    if (config.metricsDatadogType.contains("http")) {
      LOGGER.info("Enabling HTTP Datadog reporting");
      transport=new HttpTransport.Builder().withApiKey(config.metricsDatadogAPIKey).build();
    }
 else {
      LOGGER.info("Enabling UDP Datadog reporting with host " + config.metricsDatadogHost + ", port " + config.metricsDatadogPort);
      transport=new UdpTransport.Builder().withStatsdHost(config.metricsDatadogHost).withPort(config.metricsDatadogPort).build();
    }
    final DatadogReporter reporter=DatadogReporter.forRegistry(config.metricRegistry).withTransport(transport).withExpansions(EnumSet.of(COUNT,RATE_1_MINUTE,RATE_15_MINUTE,MEDIAN,P95,P99)).withTags(getDatadogTags(config.metricsDatadogTags)).build();
    reporter.start(config.metricsDatadogInterval,TimeUnit.SECONDS);
    LOGGER.info("Datadog reporting enabled");
  }
  if (config.metricsReportingType.contains(reportingTypeHttp)) {
    CollectorRegistry.defaultRegistry.register(new DropwizardExports(config.metricRegistry));
  }
}
