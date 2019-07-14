@Override public List<BeanPropertyWriter> changeProperties(SerializationConfig config,BeanDescription beanDesc,List<BeanPropertyWriter> beanProperties){
  if (!Registration.class.isAssignableFrom(beanDesc.getBeanClass())) {
    return beanProperties;
  }
  beanProperties.stream().filter(beanProperty -> "metadata".equals(beanProperty.getName())).forEach(beanProperty -> beanProperty.assignSerializer(metadataSerializer));
  return beanProperties;
}
