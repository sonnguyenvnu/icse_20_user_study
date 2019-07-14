protected void loadBeanDefinitions(DeploymentDescriptor deployment,XmlBeanDefinitionReader beanDefinitionReader){
  for (  Map.Entry<String,Resource> entry : deployment.getSpringResources().entrySet()) {
    String fileName=entry.getKey();
    beanDefinitionReader.loadBeanDefinitions(entry.getValue());
    deployment.addInstalledSpringXml(fileName);
  }
}
