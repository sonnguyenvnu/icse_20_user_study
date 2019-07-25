public void reset(DeployPluginsSettings_Configuration settings){
  List<SNodeReference> clonableListToNodes=PointerUtils.clonableListToNodes(settings.getPluginsToDeploy());
  myPluginsPanel.setData(clonableListToNodes);
}
