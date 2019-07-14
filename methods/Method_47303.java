public synchronized void setGridfiles(ArrayList<String> gridfiles){
  if (gridfiles != null) {
    for (    String gridfile : gridfiles) {
      setPathAsGridOrList(gridfile,GRID);
    }
  }
}
