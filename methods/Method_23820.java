protected Table createSubset(int[] rowSubset){
  Table newbie=new Table();
  newbie.setColumnTitles(columnTitles);
  newbie.columnTypes=columnTypes;
  newbie.setRowCount(rowSubset.length);
  for (int i=0; i < rowSubset.length; i++) {
    int row=rowSubset[i];
    for (int col=0; col < columns.length; col++) {
switch (columnTypes[col]) {
case STRING:
        newbie.setString(i,col,getString(row,col));
      break;
case INT:
    newbie.setInt(i,col,getInt(row,col));
  break;
case LONG:
newbie.setLong(i,col,getLong(row,col));
break;
case FLOAT:
newbie.setFloat(i,col,getFloat(row,col));
break;
case DOUBLE:
newbie.setDouble(i,col,getDouble(row,col));
break;
}
}
}
return newbie;
}
