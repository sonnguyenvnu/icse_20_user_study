@Override public boolean processInIOThread(){
  final Map<String,String> parameters=boltServer.serverConfig.getParameters();
  if (CommonUtils.isEmpty(parameters)) {
    return false;
  }
  String processInIOThread=parameters.get(RpcConstants.PROCESS_IN_IOTHREAD);
  return Boolean.parseBoolean(parameters.get(processInIOThread));
}
