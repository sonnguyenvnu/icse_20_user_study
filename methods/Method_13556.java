@Bean public NacosPropertySourceLocator nacosPropertySourceLocator(NacosConfigProperties nacosConfigProperties){
  return new NacosPropertySourceLocator(nacosConfigProperties);
}
