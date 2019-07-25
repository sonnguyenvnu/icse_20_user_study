public void map(Object source,Object destination){
  saveLastMapping();
  getNextMapping();
  recordSourceValue(source);
}
