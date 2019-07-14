public static FileRunner createSettingFileRunner(final File settingsFile,final StartArgs startArgs){
  return new FileRunner(){
    @Override protected Runner newRunner(){
      return new SettingRunner(toInputStream(settingsFile),startArgs);
    }
  }
;
}
