public void reset(@NotNull Configuration configuration){
  super.reset(configuration);
  setElixirArguments(configuration.getElixirArguments());
  setErlArguments(configuration.getErlArguments());
}
