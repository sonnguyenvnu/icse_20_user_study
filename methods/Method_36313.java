public boolean skipComponent(){
  String skipComponent=environment.getProperty(HealthCheckConstants.SOFABOOT_SKIP_COMPONENT_HEALTH_CHECK);
  return StringUtils.hasText(skipComponent) && "true".equalsIgnoreCase(skipComponent);
}
