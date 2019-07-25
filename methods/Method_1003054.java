private Value[] convert(Value[] values,int columnCount){
  Value[] newValues;
  if (columnCount == values.length) {
    newValues=values;
  }
 else {
    newValues=new Value[columnCount];
  }
  Mode mode=session.getDatabase().getMode();
  for (int i=0; i < columnCount; i++) {
    Expression e=expressions.get(i);
    newValues[i]=values[i].convertTo(e.getType(),mode,null);
  }
  return newValues;
}
