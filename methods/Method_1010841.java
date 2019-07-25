@Override public void reset(MPSConfigurationBean data){
  refreshSolutionDescriptorName();
  for (  SModuleConfigurationTab tab : myTabs) {
    tab.reset(data);
  }
}
