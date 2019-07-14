public static void registerSetterFactory(ConsumerConfig consumerConfig,SetterFactory setterFactory){
  SETTER_FACTORY_MAPPING.put(consumerConfig,setterFactory);
}
