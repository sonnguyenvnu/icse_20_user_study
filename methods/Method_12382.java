@Bean public SimpleModule adminJacksonModule(){
  SimpleModule module=new SimpleModule();
  module.addDeserializer(Registration.class,new RegistrationDeserializer());
  module.setSerializerModifier(new RegistrationBeanSerializerModifier(new SanitizingMapSerializer(this.adminServerProperties.getMetadataKeysToSanitize())));
  return module;
}
