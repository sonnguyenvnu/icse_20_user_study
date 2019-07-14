public static void registerFallbackFactory(ConsumerConfig consumerConfig,FallbackFactory fallbackFactory){
  FALLBACK_FACTORY_MAPPING.put(consumerConfig,fallbackFactory);
}
