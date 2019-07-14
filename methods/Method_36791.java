public void registerCompatible(String type,Class<? extends BaseCell> cellClazz){
  typeCellMap.put(type,cellClazz);
}
