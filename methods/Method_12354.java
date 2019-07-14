@Bean @ConditionalOnMissingBean public ApplicationRegistrator registrator(RegistrationClient registrationClient,ClientProperties client,ApplicationFactory applicationFactory){
  return new ApplicationRegistrator(applicationFactory,registrationClient,client.getAdminUrl(),client.isRegisterOnce());
}
