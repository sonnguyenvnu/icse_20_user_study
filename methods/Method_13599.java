private void checkSentinelRestTemplate(SentinelRestTemplate sentinelRestTemplate,String beanName){
  checkBlock4RestTemplate(sentinelRestTemplate.blockHandlerClass(),sentinelRestTemplate.blockHandler(),beanName,SentinelConstants.BLOCK_TYPE);
  checkBlock4RestTemplate(sentinelRestTemplate.fallbackClass(),sentinelRestTemplate.fallback(),beanName,SentinelConstants.FALLBACK_TYPE);
}
