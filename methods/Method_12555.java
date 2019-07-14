private RequestMappingInfo withPrefix(RequestMappingInfo mapping){
  if (!StringUtils.hasText(adminContextPath)) {
    return mapping;
  }
  PatternsRequestCondition patternsCondition=new PatternsRequestCondition(withNewPatterns(mapping.getPatternsCondition().getPatterns()));
  return new RequestMappingInfo(patternsCondition,mapping.getMethodsCondition(),mapping.getParamsCondition(),mapping.getHeadersCondition(),mapping.getConsumesCondition(),mapping.getProducesCondition(),mapping.getCustomCondition());
}
