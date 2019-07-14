public static FallbackFactory loadFallbackFactory(ConsumerConfig consumerConfig){
  FallbackFactory fallbackFactory=FALLBACK_FACTORY_MAPPING.get(consumerConfig);
  if (fallbackFactory != null) {
    return fallbackFactory;
  }
  return GLOBAL_FALLBACK_FACTORY;
}
