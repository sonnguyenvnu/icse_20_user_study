public void reset(@NotNull Configuration configuration){
  final Module module;
  if (!ProcessOutput.isSmallIde()) {
    modulesComboBox.fillModules(configuration.getProject(),ElixirModuleType.getInstance());
    module=configuration.getConfigurationModule().getModule();
  }
 else {
    module=null;
  }
  if (module != null) {
    setRunInModuleSelected(true);
    modulesComboBox.setSelectedModule(module);
  }
 else {
    setRunInModuleSelected(false);
  }
  parametersPanel.reset(configuration);
}
