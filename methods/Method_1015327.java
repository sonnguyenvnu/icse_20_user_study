@Override public void finished(ConfigurableApplicationContext context,Throwable throwable){
  Collection<ApplicationContextCustomizer> applicationContextCustomizers=Arrays.asList(new DiscoveryClientRegistrationInvoker());
  for (  ApplicationContextCustomizer customizer : applicationContextCustomizers) {
    customizer.customize(context);
  }
}
