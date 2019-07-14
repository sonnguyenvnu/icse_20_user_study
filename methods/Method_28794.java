private HostAndPort generateHostAndPort(List<Object> hostInfos){
  return new HostAndPort(SafeEncoder.encode((byte[])hostInfos.get(0)),((Long)hostInfos.get(1)).intValue());
}
