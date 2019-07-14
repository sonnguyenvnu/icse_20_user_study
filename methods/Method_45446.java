private Function<GlobalSetting,RunnerSetting> toRunnerSetting(){
  return new Function<GlobalSetting,RunnerSetting>(){
    @Override public RunnerSetting apply(    final GlobalSetting setting){
      return aRunnerSetting().addStreams(from(setting.includes()).transform(toStream()).toList()).withContext(setting.getContext()).withFileRoot(setting.getFileRoot()).withRequest(setting.getRequest()).withResponse(setting.getResponse()).build();
    }
  }
;
}
