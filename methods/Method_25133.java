@Override public int isSupportedOption(String option){
  int numberOfArgs=javacTool.isSupportedOption(option);
  if (numberOfArgs != -1) {
    return numberOfArgs;
  }
  return ErrorProneOptions.isSupportedOption(option);
}
