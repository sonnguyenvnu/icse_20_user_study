private void add(ArrayList<Row> rows,Object... stringsOrValues){
  Value[] values=new Value[stringsOrValues.length];
  for (int i=0; i < stringsOrValues.length; i++) {
    Object s=stringsOrValues[i];
    Value v=s == null ? ValueNull.INSTANCE : s instanceof String ? ValueString.get((String)s) : (Value)s;
    values[i]=columns[i].convert(v);
  }
  Row row=database.createRow(values,1);
  row.setKey(rows.size());
  rows.add(row);
}
