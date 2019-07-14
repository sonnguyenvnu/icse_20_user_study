@EventListener @Order(Ordered.LOWEST_PRECEDENCE) public void onClosedContext(ContextClosedEvent event){
  if (event.getApplicationContext().getParent() == null || "bootstrap".equals(event.getApplicationContext().getParent().getId())) {
    stopRegisterTask();
    if (autoDeregister) {
      registrator.deregister();
    }
  }
}
