public void renew(final MetaInfoRequest request){
  try {
    QMon.clientRefreshMetaInfoCountInc(request.getSubject());
    final ClientMetaInfo meta=createClientMeta(request);
    final Map<ClientMetaInfo,Long> subjectClients=allClients.computeIfAbsent(request.getSubject(),key -> new ConcurrentHashMap<>());
    subjectClients.put(meta,System.currentTimeMillis());
    final Map<ClientMetaInfo,Long> appCodeClients=allSubject.computeIfAbsent(request.getAppCode(),key -> new ConcurrentHashMap<>());
    appCodeClients.put(meta,System.currentTimeMillis());
  }
 catch (  Exception e) {
    LOG.error("refresh client info error",e);
  }
}
