@Override public void onSetBadge(int tabIndex,int count){
  TabsCountStateModel model=getModelAtIndex(tabIndex);
  if (model == null) {
    model=new TabsCountStateModel();
  }
  model.setTabIndex(tabIndex);
  model.setCount(count);
  boolean removed=counts.remove(model);
  counts.add(model);
  if (tabs != null) {
    updateCount(model);
  }
}
