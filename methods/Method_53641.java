public CycleSetModel.Cycle createNewCycle(){
  CycleSetModel.Cycle cycle=createCycle();
  CycleEdit cycleEdit=new CycleEdit(cycle.myView,cycle.myModel,animationPanel);
  cycle.myControl=cycleEdit;
  cycleEdit.setRemoveCallback(e -> removeCycle(cycle));
  int count=myCycleEditTabs.getTabCount();
  myCycleEditTabs.insertTab("label",null,cycleEdit,"tooltip",count - 1);
  cycleEdit.updateTabName(cycle.myModel.getAttName());
  myGraphs.add(cycle.myView);
  myGraphLayout.setRows(myCycleEditTabs.getTabCount() - 1);
  myGraphs.validate();
  return cycle;
}
