public Class<? extends BaseCell> getCellClass(String type){
  return typeCellMap.get(type);
}
