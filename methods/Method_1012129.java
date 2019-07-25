@Override public void reset(){
  mySaveTransientModelsCheckBox.setSelected(myGenerationSettings.isSaveTransientModels());
  myCheckModelsBeforeGenerationCheckBox.setSelected(myGenerationSettings.isCheckModelsBeforeGeneration());
  myUseNewGenerator.setSelected(myGenerationSettings.isParallelGenerator());
  myInplaceTransform.setSelected(myGenerationSettings.useInplaceTransformations());
  myAvoidDynamicRefs.setSelected(myGenerationSettings.createStaticReferences());
  myStrictMode.setSelected(myGenerationSettings.isStrictMode());
  myUseNewGenerator.setEnabled(myGenerationSettings.isStrictMode());
  myNumberOfParallelThreads.setEditable(myGenerationSettings.isParallelGenerator() && myGenerationSettings.isStrictMode());
  myNumberOfParallelThreads.setValue(myGenerationSettings.getNumberOfParallelThreads());
  myShowInfo.setSelected(myGenerationSettings.isShowInfo());
  myShowWarnings.setSelected(myGenerationSettings.isShowWarnings());
  myKeepModelsWithWarnings.setEnabled(myGenerationSettings.isShowWarnings());
  myKeepModelsWithWarnings.setSelected(myGenerationSettings.isKeepModelsWithWarnings());
  myShowBadChildWarnings.setEnabled(myGenerationSettings.isShowWarnings());
  myShowBadChildWarnings.setSelected(myGenerationSettings.isShowBadChildWarning());
  myNumberOfModelsToKeep.setEditable(myGenerationSettings.getNumberOfModelsToKeep() != -1);
  myNumberOfModelsToKeep.setValue(myGenerationSettings.getNumberOfModelsToKeep() == -1 ? 16 : myGenerationSettings.getNumberOfModelsToKeep());
  myLimitNumberOfModels.setSelected(myGenerationSettings.getNumberOfModelsToKeep() != -1);
  myGenerateDebugInfo.setSelected(myGenerationSettings.isGenerateDebugInfo());
  final JRadioButton[] allbuttons={myTraceNone,myTraceSteps,myTraceLanguages,myTraceTypes};
  allbuttons[myGenerationSettings.getPerformanceTracingLevel()].setSelected(true);
  myButtonState.reset();
}
