private Predicate<? super GlobalSetting> byEnv(final String env){
  return new Predicate<GlobalSetting>(){
    @Override public boolean apply(    final GlobalSetting globalSetting){
      return env == null || env.equalsIgnoreCase(globalSetting.getEnv());
    }
  }
;
}
