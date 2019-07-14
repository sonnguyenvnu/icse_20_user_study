private DecompilerSettings cloneSettings(){
  DecompilerSettings settings=ConfigSaver.getLoadedInstance().getDecompilerSettings();
  DecompilerSettings newSettings=new DecompilerSettings();
  if (newSettings.getJavaFormattingOptions() == null) {
    newSettings.setJavaFormattingOptions(JavaFormattingOptions.createDefault());
  }
synchronized (settings) {
    newSettings.setExcludeNestedTypes(settings.getExcludeNestedTypes());
    newSettings.setFlattenSwitchBlocks(settings.getFlattenSwitchBlocks());
    newSettings.setForceExplicitImports(settings.getForceExplicitImports());
    newSettings.setForceExplicitTypeArguments(settings.getForceExplicitTypeArguments());
    newSettings.setOutputFileHeaderText(settings.getOutputFileHeaderText());
    newSettings.setLanguage(settings.getLanguage());
    newSettings.setShowSyntheticMembers(settings.getShowSyntheticMembers());
    newSettings.setAlwaysGenerateExceptionVariableForCatchBlocks(settings.getAlwaysGenerateExceptionVariableForCatchBlocks());
    newSettings.setOutputDirectory(settings.getOutputDirectory());
    newSettings.setRetainRedundantCasts(settings.getRetainRedundantCasts());
    newSettings.setIncludeErrorDiagnostics(settings.getIncludeErrorDiagnostics());
    newSettings.setIncludeLineNumbersInBytecode(settings.getIncludeLineNumbersInBytecode());
    newSettings.setRetainPointlessSwitches(settings.getRetainPointlessSwitches());
    newSettings.setUnicodeOutputEnabled(settings.isUnicodeOutputEnabled());
    newSettings.setMergeVariables(settings.getMergeVariables());
    newSettings.setShowDebugLineNumbers(settings.getShowDebugLineNumbers());
  }
  return newSettings;
}
