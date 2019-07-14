public static String buildConsumerPath(String rootPath,AbstractInterfaceConfig config){
  return rootPath + "sofa-rpc/" + config.getInterfaceId() + "/consumers";
}
