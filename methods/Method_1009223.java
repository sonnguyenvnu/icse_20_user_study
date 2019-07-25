public void reset(@NotNull Configuration configuration){
  Module module=null;
  if (!ProcessOutput.isSmallIde()) {
    modulesComboBox.fillModules(configuration.getProject(),ElixirModuleType.getInstance());
    module=configuration.getConfigurationModule().getModule();
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
