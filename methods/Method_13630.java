@Override public void afterSingletonsInstantiated(){
  Map<String,ZuulBlockFallbackProvider> providerMap=beanFactory.getBeansOfType(ZuulBlockFallbackProvider.class);
  if (!CollectionUtils.isEmpty(providerMap)) {
    providerMap.forEach((k,v) -> {
      logger.info("[Sentinel Zuul] Register provider name:{}, instance: {}",k,v);
      ZuulBlockFallbackManager.registerProvider(v);
    }
);
  }
 else {
    logger.info("[Sentinel Zuul] Register default fallback provider. ");
    ZuulBlockFallbackManager.registerProvider(new DefaultBlockFallbackProvider());
  }
}
