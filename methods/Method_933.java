private static Properties initProperties(){
  LinkedProperties props=new LinkedProperties();
  ClassLoader classLoader=NameListServiceImpl.class.getClassLoader();
  try {
    props.load(classLoader.getResourceAsStream(NAME_LIST_PROPERTY_FILE_NAME));
  }
 catch (  IOException ex) {
    throw new IllegalStateException("Load namelist.properties fail",ex);
  }
  return props;
}
