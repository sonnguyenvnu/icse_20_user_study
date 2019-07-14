@EventListener @Order(Ordered.LOWEST_PRECEDENCE) public void onApplicationReady(ApplicationReadyEvent event){
  if (autoRegister) {
    startRegisterTask();
  }
}
