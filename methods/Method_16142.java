@Bean @ConditionalOnClass(name="io.swagger.annotations.Api") public SwaggerAccessLoggerParser swaggerAccessLoggerParser(){
  return new SwaggerAccessLoggerParser();
}
