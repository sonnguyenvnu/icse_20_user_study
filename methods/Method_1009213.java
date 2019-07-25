public void reset(@NotNull Configuration configuration){
  super.reset(configuration);
  setErlArguments(configuration.getErlArguments());
}
