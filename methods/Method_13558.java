public static void collectNacosPropertySources(NacosPropertySource nacosPropertySource){
  NACOS_PROPERTY_SOURCE_REPOSITORY.putIfAbsent(nacosPropertySource.getDataId(),nacosPropertySource);
}
