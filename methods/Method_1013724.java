@Override public Pair<TunnelSocketStatsMetric,TunnelSocketStatsMetric> analyze(TunnelSocketStatsResult result){
  Pair<TunnelSocketStatsMetric,TunnelSocketStatsMetric> frontendAndBackend=new Pair<>();
  frontendAndBackend.setKey(new TunnelSocketStatsMetric(getType(),analyze(result.getFrontendSocketStats().getResult())));
  frontendAndBackend.setValue(new TunnelSocketStatsMetric(getType(),analyze(result.getBackendSocketStats().getResult())));
  return frontendAndBackend;
}
