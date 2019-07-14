public static void registerFallback(ConsumerConfig consumerConfig,Object fallback){
  FALLBACK_FACTORY_MAPPING.put(consumerConfig,new DefaultFallbackFactory<Object>(fallback));
}
