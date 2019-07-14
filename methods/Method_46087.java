private void checkPass(NewService service){
  try {
    consulClient.agentCheckPass("service:" + service.getId(),"TTL check passing by SOFA RPC");
  }
 catch (  Exception e) {
    LOGGER.error("Consul check pass failed.",e);
  }
}
