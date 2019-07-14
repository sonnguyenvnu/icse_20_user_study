/** 
 * ?????
 * @param appName     ??
 * @param serviceName ??
 * @return ??????????
 */
private SofaRpcException cannotFoundService(String appName,String serviceName){
  String errorMsg=LogCodes.getLog(LogCodes.ERROR_PROVIDER_SERVICE_CANNOT_FOUND,serviceName);
  LOGGER.errorWithApp(appName,errorMsg);
  return new SofaRpcException(RpcErrorType.SERVER_NOT_FOUND_INVOKER,errorMsg);
}
