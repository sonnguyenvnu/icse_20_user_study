@EventListener(ServiceBeanExportedEvent.class) public void onServiceBeanExported(ServiceBeanExportedEvent event){
  ServiceBean serviceBean=event.getServiceBean();
  publishServiceRestMetadata(serviceBean);
}
