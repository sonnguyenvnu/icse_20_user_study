private String[] determineConfigurations(Map<String,String> envVars){
  if (envVars.containsKey(ConfigUtils.CONFIGURATIONS)) {
    return StringUtils.tokenizeToStringArray(envVars.get(ConfigUtils.CONFIGURATIONS),",");
  }
  if (System.getProperties().containsKey(ConfigUtils.CONFIGURATIONS)) {
    return StringUtils.tokenizeToStringArray(System.getProperties().getProperty(ConfigUtils.CONFIGURATIONS),",");
  }
  if (configurations != null) {
    return configurations;
  }
  if (extension.configurations != null) {
    return extension.configurations;
  }
  if (getProject().getGradle().getGradleVersion().startsWith("3")) {
    return DEFAULT_CONFIGURATIONS_GRADLE3;
  }
  return DEFAULT_CONFIGURATIONS_GRADLE45;
}
