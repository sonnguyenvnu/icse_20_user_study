@JsonProperty("hasRecords") public boolean hasRecords(){
  return _records != null && _rowDependencies != null && _records.size() < _rowDependencies.size();
}
