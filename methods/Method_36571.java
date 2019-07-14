private SofaRuntimeContext sofaRuntimeContext(String appName,BindingConverterFactory bindingConverterFactory,BindingAdapterFactory bindingAdapterFactory){
  ClientFactoryInternal clientFactoryInternal=new ClientFactoryImpl();
  SofaRuntimeManager sofaRuntimeManager=new StandardSofaRuntimeManager(appName,SofaRuntimeAutoConfiguration.class.getClassLoader(),clientFactoryInternal);
  sofaRuntimeManager.getComponentManager().registerComponentClient(ReferenceClient.class,new ReferenceClientImpl(sofaRuntimeManager.getSofaRuntimeContext(),bindingConverterFactory,bindingAdapterFactory));
  sofaRuntimeManager.getComponentManager().registerComponentClient(ServiceClient.class,new ServiceClientImpl(sofaRuntimeManager.getSofaRuntimeContext(),bindingConverterFactory,bindingAdapterFactory));
  SofaFramework.registerSofaRuntimeManager(sofaRuntimeManager);
  return sofaRuntimeManager.getSofaRuntimeContext();
}
