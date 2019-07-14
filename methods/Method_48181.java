protected String getPath(ConfigElement option,String... umbrellaElements){
  verifyElement(option);
  return ConfigElement.getPath(option,umbrellaElements);
}
