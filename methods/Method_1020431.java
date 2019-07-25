static OcAgentTraceServiceConfigRpcHandler create(TraceServiceStub stub,TraceConfig traceConfig){
  OcAgentTraceServiceConfigRpcHandler configRpcHandler=new OcAgentTraceServiceConfigRpcHandler(traceConfig);
  UpdatedLibraryConfigObserver updatedLibraryConfigObserver=new UpdatedLibraryConfigObserver(traceConfig,configRpcHandler);
  try {
    StreamObserver<CurrentLibraryConfig> currentConfigObserver=stub.config(updatedLibraryConfigObserver);
    configRpcHandler.setCurrentConfigObserver(currentConfigObserver);
  }
 catch (  StatusRuntimeException e) {
    configRpcHandler.onComplete(e);
  }
  return configRpcHandler;
}
