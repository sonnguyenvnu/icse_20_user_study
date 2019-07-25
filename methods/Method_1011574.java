public void apply(@Nullable JavaRunParameters javaOptions){
  if (javaOptions == null) {
    return;
  }
  javaOptions.programParameters(myProgramParameters.getText());
  javaOptions.vmOptions(myVmParameters.getText());
  javaOptions.jrePath(myJreHome.getText());
  javaOptions.workingDirectory(myWorkingDirectory.getText());
  javaOptions.useAlternativeJre(myUseAlternativeJre.isSelected());
}
