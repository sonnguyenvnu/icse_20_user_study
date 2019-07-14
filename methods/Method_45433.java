public static FileRunner createConfigurationFileRunner(final Iterable<File> files,final StartArgs startArgs){
  return new FileRunner(){
    @Override protected Runner newRunner(){
      return newJsonRunnerWithStreams(toInputStreams(files),startArgs);
    }
  }
;
}
