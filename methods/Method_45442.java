private static Function<InputStream,RunnerSetting> toRunnerSetting(){
  return new Function<InputStream,RunnerSetting>(){
    @Override public RunnerSetting apply(    final InputStream input){
      return aRunnerSetting().addStream(input).build();
    }
  }
;
}
