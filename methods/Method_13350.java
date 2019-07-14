@EventListener(ApplicationFailedEvent.class) public void onApplicationFailed(){
  unExportDubboMetadataConfigService();
}
