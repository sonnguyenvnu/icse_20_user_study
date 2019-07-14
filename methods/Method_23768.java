public TableRow setRow(int row,TableRow source){
  ensureBounds(row,source.getColumnCount() - 1);
  for (int col=0; col < Math.min(source.getColumnCount(),columns.length); col++) {
switch (columnTypes[col]) {
case INT:
      setInt(row,col,source.getInt(col));
    break;
case LONG:
  setLong(row,col,source.getLong(col));
break;
case FLOAT:
setFloat(row,col,source.getFloat(col));
break;
case DOUBLE:
setDouble(row,col,source.getDouble(col));
break;
case STRING:
setString(row,col,source.getString(col));
break;
case CATEGORY:
int index=source.getInt(col);
setInt(row,col,index);
if (!columnCategories[col].hasCategory(index)) {
columnCategories[col].setCategory(index,source.getString(col));
}
break;
default :
throw new RuntimeException("no types");
}
}
return new RowPointer(this,row);
}
