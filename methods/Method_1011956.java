@Override public void apply(){
  getPreferencePage().commit();
  CompilerState compilerState=new CompilerState();
  JavaVersion selectedTargetJavaVersion=getPreferencePage().getSelectedTargetJavaVersion();
  if (selectedTargetJavaVersion != null) {
    compilerState.setTargetVersion(selectedTargetJavaVersion.getCompilerVersion());
  }
 else {
    compilerState.setTargetVersion(null);
  }
  CompilerSettingsComponent.getInstance(myProject).loadState(compilerState);
}
