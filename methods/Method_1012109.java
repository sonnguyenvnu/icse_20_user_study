@Override public void init(){
  int rowCount=2 + ((myExtraPanels == null ? 0 : myExtraPanels.length));
  int rowIndex=0;
  this.setLayout(new GridLayoutManager(rowCount,1));
  this.setAutoscrolls(false);
  this.add(createProjectModulesList(),getGridConstraints(rowIndex++,true));
  add(myMakeGroup.createComponent(),getGridConstraints(rowIndex++,false));
  for (  ProjectPrefsExtraPanel extraPanel : myExtraPanels) {
    this.add(extraPanel.getComponent(),getGridConstraints(rowIndex++,false));
  }
}
