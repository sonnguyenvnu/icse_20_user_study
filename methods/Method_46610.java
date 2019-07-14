public RpcExecuteService loadRpcExecuteService(String transactionType,LCNCmdType cmdType){
  return loadRpcExecuteService(getRpcBeanName(transactionType,cmdType));
}
