public void insertColumn(int index,String title,int type){
  if (title != null && columnTitles == null) {
    columnTitles=new String[columns.length];
  }
  if (columnTitles != null) {
    columnTitles=PApplet.splice(columnTitles,title,index);
    columnIndices=null;
  }
  columnTypes=PApplet.splice(columnTypes,type,index);
  HashMapBlows[] catTemp=new HashMapBlows[columns.length + 1];
  for (int i=0; i < index; i++) {
    catTemp[i]=columnCategories[i];
  }
  catTemp[index]=new HashMapBlows();
  for (int i=index; i < columns.length; i++) {
    catTemp[i + 1]=columnCategories[i];
  }
  columnCategories=catTemp;
  Object[] temp=new Object[columns.length + 1];
  System.arraycopy(columns,0,temp,0,index);
  System.arraycopy(columns,index,temp,index + 1,columns.length - index);
  columns=temp;
switch (type) {
case INT:
    columns[index]=new int[rowCount];
  break;
case LONG:
columns[index]=new long[rowCount];
break;
case FLOAT:
columns[index]=new float[rowCount];
break;
case DOUBLE:
columns[index]=new double[rowCount];
break;
case STRING:
columns[index]=new String[rowCount];
break;
case CATEGORY:
columns[index]=new int[rowCount];
break;
}
}
