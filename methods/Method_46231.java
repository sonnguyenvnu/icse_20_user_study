/** 
 * ????????????????????????????
 * @param appName       ??
 * @param serviceName   ??
 * @param remoteAddress ????
 * @return ?????
 */
private SofaRpcException clientTimeoutWhenReceiveRequest(String appName,String serviceName,String remoteAddress){
  String errorMsg=LogCodes.getLog(LogCodes.ERROR_DISCARD_TIMEOUT_REQUEST,serviceName,remoteAddress);
  if (LOGGER.isWarnEnabled(appName)) {
    LOGGER.warnWithApp(appName,errorMsg);
  }
  return new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,errorMsg);
}
