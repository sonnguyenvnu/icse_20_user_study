public static MappingBuilder requestMatching(String customRequestMatcherName){
  return new BasicMappingBuilder(customRequestMatcherName,Parameters.empty());
}
