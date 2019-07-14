@Bean @ConditionalOnMissingBean public RpcAnswer rpcClientAnswer(){
  return rpcCmd -> log.info("cmd->{}",rpcCmd);
}
