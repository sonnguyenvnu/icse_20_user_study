@Bean @ConditionalOnMissingBean public RegistrationApplicationListener registrationListener(ClientProperties client,ApplicationRegistrator registrator,Environment environment){
  RegistrationApplicationListener listener=new RegistrationApplicationListener(registrator);
  listener.setAutoRegister(client.isAutoRegistration());
  listener.setAutoDeregister(client.isAutoDeregistration(environment));
  listener.setRegisterPeriod(client.getPeriod());
  return listener;
}
