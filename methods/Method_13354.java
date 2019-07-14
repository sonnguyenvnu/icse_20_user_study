private void attachDubboMetadataServiceMetadata(Registration registration){
  if (registration == null) {
    return;
  }
synchronized (registration) {
    Map<String,String> metadata=registration.getMetadata();
    attachDubboMetadataServiceMetadata(metadata);
  }
}
