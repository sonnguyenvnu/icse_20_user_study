public static String buildProviderPath(String rootPath,AbstractInterfaceConfig config){
  return rootPath + "sofa-rpc/" + config.getInterfaceId() + "/providers";
}
