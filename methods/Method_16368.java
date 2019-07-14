protected JSR303AnnotationInfo createValidatorAnnotation(String config){
  if (config.startsWith("{")) {
    JSONObject jsonConfig=JSON.parseObject(config);
    String type=jsonConfig.getString("type");
    return strategies.stream().filter(strategy -> strategy.support(type)).findFirst().map(strategy -> strategy.parse(jsonConfig)).orElse(null);
  }
  return null;
}
