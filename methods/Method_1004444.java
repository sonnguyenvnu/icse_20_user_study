private void refresh(){
  try {
    long updateTime=store.now();
    List<ClientOfflineState> states=store.selectAll();
    Map<String,OnOfflineState> groupStateMap=new HashMap<>();
    Map<String,OnOfflineState> clientStateMap=new HashMap<>();
    for (    ClientOfflineState state : states) {
      if (state == null)       continue;
      String key=clientStateKey(state.getClientId(),state.getSubject(),state.getConsumerGroup());
      if (CLIENTID_OF_GROUP.equals(state.getClientId())) {
        groupStateMap.put(key,state.getState());
      }
 else {
        clientStateMap.put(key,state.getState());
      }
    }
    this.updateTime=updateTime;
    this.groupStateMap=groupStateMap;
    this.clientStateMap=clientStateMap;
    log.info("refreshed onoffline state {}",updateTime);
  }
 catch (  Exception e) {
    log.error("refresh OfflineState exception",e);
    REFRESH_ERROR.inc();
  }
}
