@Override public void callback(RpcCmd rpcCmd){
  executorService.submit(() -> {
    log.debug("Receive Message: {}",rpcCmd.getMsg());
    TransactionCmd transactionCmd=MessageParser.parser(rpcCmd);
    String transactionType=transactionCmd.getTransactionType();
    String action=transactionCmd.getMsg().getAction();
    RpcExecuteService executeService=transactionBeanHelper.loadRpcExecuteService(transactionType,transactionCmd.getType());
    MessageDto messageDto=null;
    try {
      Serializable message=executeService.execute(transactionCmd);
      messageDto=MessageCreator.notifyUnitOkResponse(message,action);
    }
 catch (    TxClientException e) {
      log.error("message > execute error.",e);
      messageDto=MessageCreator.notifyUnitFailResponse(e,action);
    }
catch (    Throwable e) {
      e.printStackTrace();
    }
 finally {
      if (Objects.nonNull(rpcCmd.getKey())) {
        try {
          rpcCmd.setMsg(messageDto);
          rpcClient.send(rpcCmd);
        }
 catch (        RpcException e) {
          log.error("response request[{}] error. error message: {}",rpcCmd.getKey(),e.getMessage());
        }
      }
    }
  }
);
}
