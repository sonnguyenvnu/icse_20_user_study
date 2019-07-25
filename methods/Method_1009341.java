public D map(Object subject){
  saveLastMapping();
  getNextMapping();
  recordSourceValue(subject);
  return destination;
}
