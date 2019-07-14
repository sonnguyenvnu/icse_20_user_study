@Override public boolean timeoutDiscard(){
  final Map<String,String> parameters=boltServer.serverConfig.getParameters();
  if (CommonUtils.isEmpty(parameters)) {
    return false;
  }
  String timeoutDiscard=parameters.get(RpcConstants.TIMEOUT_DISCARD_IN_SERVER);
  return Boolean.parseBoolean(parameters.get(timeoutDiscard));
}
