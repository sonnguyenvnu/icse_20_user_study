@Override public void apply() throws ConfigurationException {
  getSettings().pluginEnabled=pluginEnabled.isSelected();
  getSettings().pathToTranslation=pathToTranslationRootTextField.getText();
  getSettings().remoteDevFileScheduler=enableSchedulerCheckBox.isSelected();
  getSettings().codeFoldingPhpRoute=codeFoldingPhpRoute.isSelected();
  getSettings().codeFoldingPhpModel=codeFoldingPhpModel.isSelected();
  getSettings().codeFoldingPhpTemplate=codeFoldingPhpTemplate.isSelected();
  getSettings().codeFoldingTwigRoute=codeFoldingTwigRoute.isSelected();
  getSettings().codeFoldingTwigTemplate=codeFoldingTwigTemplate.isSelected();
  getSettings().codeFoldingTwigConstant=codeFoldingTwigConstant.isSelected();
  getSettings().directoryToApp=directoryToApp.getText();
  getSettings().directoryToWeb=directoryToWeb.getText();
}
