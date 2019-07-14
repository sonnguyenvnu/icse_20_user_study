/** 
 * build service name for config? format: interface[:uniqueId]:protocol 1. here we didn't use protocol like other registry with symbol '@' but ':' because the limit of Nacos Service Name can only have these characters: '0-9a-zA-Z.:_-', and ':' won't corrupt with uniqueId because it'll always at the end of the service name. 2. here we didn't use ConfigUniqueNameGenerator.getUniqueName() because I think this method is only for old version compatible, and here we needn't version here anymore.
 * @param config   producer config or consumer config
 * @param protocol protocol for config
 * @return unique service name
 */
static String buildServiceName(AbstractInterfaceConfig config,String protocol){
  if (RpcConstants.PROTOCOL_TYPE_BOLT.equals(protocol) || RpcConstants.PROTOCOL_TYPE_TR.equals(protocol)) {
    return ConfigUniqueNameGenerator.getServiceName(config) + ":DEFAULT";
  }
 else {
    return ConfigUniqueNameGenerator.getServiceName(config) + ":" + protocol;
  }
}
