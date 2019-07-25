public static StreamExecutionEnvironment prepare(ParameterTool parameterTool) throws Exception {
  StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
  env.setParallelism(parameterTool.getInt(PropertiesConstants.STREAM_PARALLELISM,5));
  env.getConfig().disableSysoutLogging();
  env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4,10000));
  if (parameterTool.getBoolean(PropertiesConstants.STREAM_CHECKPOINT_ENABLE,true)) {
    env.enableCheckpointing(parameterTool.getInt(PropertiesConstants.STREAM_CHECKPOINT_INTERVAL,1000));
  }
  env.getConfig().setGlobalJobParameters(parameterTool);
  env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
  return env;
}
