public void apply(DeployPluginsSettings_Configuration settings){
  ClonableList<String> list=settings.getPluginsToDeploy();
  list.clear();
  ListSequence.fromList(list.getData()).addSequence(ListSequence.fromList(myPluginsPanel.getItems()).select(new ISelector<SNodeReference,String>(){
    public String select(    SNodeReference it){
      return PointerUtils.pointerToString(it);
    }
  }
));
}
