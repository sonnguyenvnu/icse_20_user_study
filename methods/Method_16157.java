private CorsConfiguration buildConfiguration(CorsProperties.CorsConfiguration config){
  CorsConfiguration corsConfiguration=new CorsConfiguration();
  corsConfiguration.setAllowedHeaders(config.getAllowedHeaders());
  corsConfiguration.setAllowedMethods(config.getAllowedMethods());
  corsConfiguration.setAllowedOrigins(config.getAllowedOrigins());
  corsConfiguration.setAllowCredentials(config.getAllowCredentials());
  corsConfiguration.setExposedHeaders(config.getExposedHeaders());
  corsConfiguration.setMaxAge(config.getMaxAge());
  return corsConfiguration;
}
