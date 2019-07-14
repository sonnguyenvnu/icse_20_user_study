public RowDependency getRowDependency(int rowIndex){
  return _rowDependencies != null && rowIndex >= 0 && rowIndex < _rowDependencies.size() ? _rowDependencies.get(rowIndex) : null;
}
