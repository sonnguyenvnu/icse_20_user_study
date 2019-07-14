private Environment createEnvironment(ConfigInfo configInfo,String application,String profile){
  Environment environment=new Environment(application,profile);
  Properties properties=createProperties(configInfo);
  String propertySourceName=String.format("Nacos[application : %s , profile : %s]",application,profile);
  PropertySource propertySource=new PropertySource(propertySourceName,properties);
  environment.add(propertySource);
  return environment;
}
