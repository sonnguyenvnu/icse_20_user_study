public void reset(){
  setModuleName(getDefaultModuleName());
  if (myProjectPath != null) {
    setModuleLocation(getDefaultModulePath() + getDefaultModuleName());
  }
}
