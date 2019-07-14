public static String buildConfigPath(String rootPath,AbstractInterfaceConfig config){
  return rootPath + "sofa-rpc/" + config.getInterfaceId() + "/configs";
}
