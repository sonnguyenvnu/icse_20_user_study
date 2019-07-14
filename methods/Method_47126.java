public void switchView(){
  boolean isPathLayoutGrid=dataUtils.getListOrGridForPath(CURRENT_PATH,DataUtils.LIST) == DataUtils.GRID;
  reloadListElements(false,results,isPathLayoutGrid);
}
