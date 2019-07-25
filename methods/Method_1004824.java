public static void populate(Settings settings){
  Iterable<EsToken> esTokens=UserProvider.create(settings).getUser().getAllEsTokens();
  if (LOG.isDebugEnabled()) {
    LOG.debug("Found list of tokens on worker: " + esTokens);
  }
  Iterator<EsToken> iterator=esTokens.iterator();
  if (iterator.hasNext()) {
    EsToken esToken=iterator.next();
    if (LOG.isDebugEnabled()) {
      LOG.debug("Using token: " + esToken);
    }
    ClusterInfo clusterInfo=new ClusterInfo(new ClusterName(esToken.getClusterName(),null),esToken.getMajorVersion());
    if (LOG.isDebugEnabled()) {
      LOG.debug("Using clusterInfo : " + clusterInfo);
    }
    settings.setInternalClusterInfo(clusterInfo);
  }
 else {
    LOG.debug("Could not locate any tokens");
  }
}
