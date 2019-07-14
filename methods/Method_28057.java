@Override public void onSetBadge(int tabIndex,int count){
  TabsCountStateModel model=new TabsCountStateModel();
  model.setTabIndex(tabIndex);
  model.setCount(count);
  counts.add(model);
  if (tabs != null) {
    updateCount(model);
  }
}
