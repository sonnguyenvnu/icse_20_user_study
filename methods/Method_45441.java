public static JsonRunner newJsonRunnerWithStreams(final Iterable<? extends InputStream> streams,final StartArgs startArgs){
  return newJsonRunnerWithSetting(from(streams).transform(toRunnerSetting()),startArgs);
}
