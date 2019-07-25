public void reset(@NotNull Configuration configuration){
  super.reset(configuration);
  setReleaseCLIPath(configuration.getReleaseCLIPath());
  setErlArguments(configuration.getErlArguments());
  setExtraArguments(configuration.getExtraArguments());
  setCodeLoadingMode(configuration.getCodeLoadingMode());
  setLogDirectory(configuration.getLogDirectory());
  setReplaceOSVars(configuration.getReplaceOSVars());
  setSysConfigPath(configuration.getSysConfigPath());
  setReleaseConfigDirectory(configuration.getReleaseConfigDirectory());
  setPipeDirectory(configuration.getPipeDirectory());
  setWantsPTY(configuration.getWantsPTY());
}
