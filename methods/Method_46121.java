/** 
 * ???????
 * @param serviceName  ?????
 * @param providerInfo ???????
 */
protected void doUnRegister(String serviceName,ProviderInfo providerInfo){
  UnPublishServiceRequest unPublishServiceRequest=new UnPublishServiceRequest();
  unPublishServiceRequest.setServiceName(serviceName);
  client.unPublishService(unPublishServiceRequest);
}
