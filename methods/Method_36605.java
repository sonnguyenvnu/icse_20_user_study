public void init(){
  sampleJvmService.message();
  sampleJvmServiceByFieldAnnotation.message();
  ReferenceClient referenceClient=clientFactory.getClient(ReferenceClient.class);
  ReferenceParam<SampleJvmService> referenceParam=new ReferenceParam<>();
  referenceParam.setInterfaceType(SampleJvmService.class);
  referenceParam.setUniqueId("serviceClientImpl");
  SampleJvmService sampleJvmServiceClientImpl=referenceClient.reference(referenceParam);
  sampleJvmServiceClientImpl.message();
}
