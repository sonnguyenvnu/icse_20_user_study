public void reset(){
  myMakeGroup.reset();
  myProperties.loadFrom(myProject);
  for (  ProjectPrefsExtraPanel ep : myExtraPanels) {
    ep.reset();
  }
}
