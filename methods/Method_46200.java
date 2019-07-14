static String buildOverridePath(String rootPath,AbstractInterfaceConfig config){
  return rootPath + "sofa-rpc/" + config.getInterfaceId() + "/overrides";
}
