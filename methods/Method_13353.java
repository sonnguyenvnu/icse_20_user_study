@EventListener(ServiceInstancePreRegisteredEvent.class) public void onServiceInstancePreRegistered(ServiceInstancePreRegisteredEvent event){
  Registration registration=event.getSource();
  attachDubboMetadataServiceMetadata(registration);
}
