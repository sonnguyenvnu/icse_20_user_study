public void reset(@Nullable JavaRunParameters javaOptions){
  if (javaOptions == null) {
    return;
  }
  myProgramParameters.setText(javaOptions.programParameters());
  myVmParameters.setText(javaOptions.vmOptions());
  myJreHome.setText(javaOptions.jrePath());
  myWorkingDirectory.setText(javaOptions.workingDirectory());
  myUseAlternativeJre.setSelected((boolean)javaOptions.useAlternativeJre());
  myJreHome.setEditable((boolean)javaOptions.useAlternativeJre());
}
