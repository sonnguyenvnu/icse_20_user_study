@Bean public static PropertySourcesPlaceholderConfigurer properties(){
  PropertySourcesPlaceholderConfigurer configurer=new PropertySourcesPlaceholderConfigurer();
  YamlPropertiesFactoryBean yaml=new YamlPropertiesFactoryBean();
  yaml.setResources(new ClassPathResource("application.yml"));
  configurer.setProperties(yaml.getObject());
  return configurer;
}
