private Properties createProperties(){
  Properties properties=new Properties();
  for (  Parameter parameter : parameters) {
    properties.put(parameter.getName(),parameter.getValue());
  }
  return properties;
}
