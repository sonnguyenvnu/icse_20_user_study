public Set<DataAccessConfig> getDataAccesses(){
  if (dataAccesses == null) {
    dataAccesses=new java.util.HashSet<>();
  }
  return dataAccesses;
}
