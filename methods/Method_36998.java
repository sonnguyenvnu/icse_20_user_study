public boolean mountView(BaseCell cell,View view){
  boolean ret=false;
  if (cell.componentInfo != null) {
    ret=renderServiceMap.get(cell.componentInfo.getType()).mountView(cell.extras,view);
  }
  return ret;
}
