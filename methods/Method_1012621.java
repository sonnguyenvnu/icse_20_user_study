private URL locate(){
  String propsFileName=System.getProperty(OPTIONS_FILE_PROPERTY,DEFAULT_OPTIONS_FILE);
  if (null == propsFileName || propsFileName.isEmpty()) {
    propsFileName=DEFAULT_OPTIONS_FILE;
  }
  return P6Util.locateFile(propsFileName);
}
