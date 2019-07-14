private Object getDefaultHandler(final JobProperties.JobPropertiesEnum jobPropertiesEnum,final String handlerClassName){
  log.warn("Cannot instantiation class '{}', use default '{}' class.",handlerClassName,jobPropertiesEnum.getKey());
  try {
    return Class.forName(jobPropertiesEnum.getDefaultValue()).newInstance();
  }
 catch (  final ClassNotFoundException|InstantiationException|IllegalAccessException e) {
    throw new JobSystemException(e);
  }
}
