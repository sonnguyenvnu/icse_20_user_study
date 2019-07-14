private void doProcess(ApplicationRuntimeModel application) throws Exception {
  outputModulesMessage(application);
  SpringContextLoader springContextLoader=createSpringContextLoader();
  installSpringContext(application,springContextLoader);
  if (applicationContext.getBean(SofaModuleFrameworkConstants.SOFA_MODULE_PROPERTIES_BEAN_ID,SofaModuleProperties.class).isModuleStartUpParallel()) {
    refreshSpringContextParallel(application);
  }
 else {
    refreshSpringContext(application);
  }
}
