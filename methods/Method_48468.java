public String getBackendDescription(){
  String className=configuration.get(STORAGE_BACKEND);
  if (className.equalsIgnoreCase("berkeleyje")) {
    return className + ":" + configuration.get(STORAGE_DIRECTORY);
  }
 else {
    return className + ":" + Arrays.toString(configuration.get(STORAGE_HOSTS));
  }
}
