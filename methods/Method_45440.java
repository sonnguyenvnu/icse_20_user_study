private MocoConfig[] toConfigs(final RunnerSetting setting){
  ImmutableList.Builder<MocoConfig> builder=ImmutableList.builder();
  addConfig(builder,setting.context());
  addConfig(builder,setting.fileRoot());
  addConfig(builder,setting.request());
  addConfig(builder,setting.response());
  return toArray(builder.build(),MocoConfig.class);
}
