@Override public void connected(String remoteKey){
  rpcEnvStatusListeners.forEach(rpcEnvStatusListener -> rpcEnvStatusListener.onConnected(remoteKey));
  new Thread(() -> {
    try {
      log.info("Send init message to TM[{}]",remoteKey);
      MessageDto msg=rpcClient.request(remoteKey,MessageCreator.initClient(applicationName,modIdProvider.modId()),5000);
      if (MessageUtils.statusOk(msg)) {
        InitClientParams resParams=msg.loadBean(InitClientParams.class);
        txClientConfig.applyDtxTime(resParams.getDtxTime());
        txClientConfig.applyTmRpcTimeout(resParams.getTmRpcTimeout());
        txClientConfig.applyMachineId(resParams.getMachineId());
        IdGenInit.applyDefaultIdGen(resParams.getSeqLen(),resParams.getMachineId());
        log.info("Finally, determined dtx time is {}ms, tm rpc timeout is {} ms, machineId is {}",resParams.getDtxTime(),resParams.getTmRpcTimeout(),resParams.getMachineId());
        rpcEnvStatusListeners.forEach(rpcEnvStatusListener -> rpcEnvStatusListener.onInitialized(remoteKey));
        return;
      }
      log.error("TM[{}] exception. connect fail!",remoteKey);
    }
 catch (    RpcException e) {
      log.error("Send init message exception: {}. connect fail!",e.getMessage());
    }
  }
).start();
}
