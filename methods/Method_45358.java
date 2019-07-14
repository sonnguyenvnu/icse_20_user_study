public final T parseServer(final ImmutableList<InputStream> streams,final Optional<Integer> port,final MocoConfig... configs){
  ImmutableList<SessionSetting> settings=Jsons.toObjects(streams,SessionSetting.class);
  return createServer(settings,port.or(0),configs);
}
