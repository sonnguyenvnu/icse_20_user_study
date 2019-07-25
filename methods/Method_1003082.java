private Value compare(Value l,ResultInterface rows){
  Value[] currentRow=rows.currentRow();
  Value r=l.getValueType() != Value.ROW && query.getColumnCount() == 1 ? currentRow[0] : ValueRow.get(currentRow);
  return Comparison.compare(database,l,r,compareType);
}
