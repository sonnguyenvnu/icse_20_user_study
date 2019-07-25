@Override public void record(Tunnel tunnel){
  StringBuilder sb=new StringBuilder(tunnel.identity().toString());
  sb.append(RedisProtocol.CRLF);
  sb.append(tunnel.getTunnelMonitor().getTunnelStats().getTunnelStatsResult().toString()).append(RedisProtocol.CRLF);
  sb.append(SESSION_TYPE.FRONTEND.name()).append(RedisProtocol.CRLF);
  sb.append("outbound buffer: ").append(tunnel.getTunnelMonitor().getFrontendSessionMonitor().getOutboundBufferMonitor().getOutboundBufferCumulation()).append(RedisProtocol.CRLF);
  sb.append(tunnel.getTunnelMonitor().getFrontendSessionMonitor().getSocketStats().getSocketStatsResult().toString()).append(RedisProtocol.CRLF);
  sb.append(tunnel.getTunnelMonitor().getFrontendSessionMonitor().getSessionStats().toString()).append(RedisProtocol.CRLF);
  sb.append(SESSION_TYPE.BACKEND.name()).append(RedisProtocol.CRLF);
  sb.append("outbound buffer: ").append(tunnel.getTunnelMonitor().getBackendSessionMonitor().getOutboundBufferMonitor().getOutboundBufferCumulation()).append(RedisProtocol.CRLF);
  sb.append(tunnel.getTunnelMonitor().getBackendSessionMonitor().getSocketStats().getSocketStatsResult().toString()).append(RedisProtocol.CRLF);
  sb.append(tunnel.getTunnelMonitor().getBackendSessionMonitor().getSessionStats().toString()).append(RedisProtocol.CRLF);
  sb.append(LINE_SPLITTER).append(RedisProtocol.CRLF);
  logger.info("{}",sb.toString());
}
