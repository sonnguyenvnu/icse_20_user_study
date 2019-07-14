@Override public void initView(){
  tabClickIds=getTabClickIds();
  vTabClickViews=new View[getCount()];
  for (int i=0; i < getCount(); i++) {
    vTabClickViews[i]=findViewById(tabClickIds[i]);
  }
  int[][] tabSelectIds=getTabSelectIds();
  if (tabSelectIds != null && tabSelectIds.length > 0) {
    vTabSelectViews=new View[tabSelectIds.length][getCount()];
    for (int i=0; i < tabSelectIds.length; i++) {
      if (tabSelectIds[i] != null) {
        for (int j=0; j < tabSelectIds[i].length; j++) {
          vTabSelectViews[i][j]=findViewById(tabSelectIds[i][j]);
        }
      }
    }
  }
}
