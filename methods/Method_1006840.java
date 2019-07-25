@ConditionalOnMissingBean(Brave.class) @ConditionalOnBean(SpanCollector.class) @Bean public Brave brave(SpanCollector spanCollector,Environment env){
  String applicationName=env.getProperty("spring.application.name");
  if (StringUtil.isNullOrEmpty(applicationName))   throw new RuntimeException("spring.application.name=null, config it or #tracing.zipkin.url=");
  Brave.Builder builder=new Brave.Builder(applicationName);
  builder.spanCollector(spanCollector);
  builder.traceSampler(Sampler.create(properties.getSampleRate()));
  logger.info("Tracing(ZipKin): Brave instance created, default add tracing to ReyClient");
  logger.info("Config Zipkin Servlet Tracing by: @EnableTracingServlet");
  logger.info("create more tracing filter or interceptor for spring boot project, by parameter (Brave brave), like code as follows: ");
  logger.info("       @ConditionalOnMissingBean(BraveServletFilter.class)");
  logger.info("       @ConditionalOnBean(Brave.class)");
  logger.info("       @Bean");
  logger.info("       public BraveServletFilter braveServletFilter(Brave brave) {");
  return builder.build();
}
