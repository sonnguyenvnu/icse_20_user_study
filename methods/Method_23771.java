public void insertRow(int insert,Object[] columnData){
  for (int col=0; col < columns.length; col++) {
switch (columnTypes[col]) {
case CATEGORY:
case INT:
{
        int[] intTemp=new int[rowCount + 1];
        System.arraycopy(columns[col],0,intTemp,0,insert);
        System.arraycopy(columns[col],insert,intTemp,insert + 1,rowCount - insert);
        columns[col]=intTemp;
        break;
      }
case LONG:
{
      long[] longTemp=new long[rowCount + 1];
      System.arraycopy(columns[col],0,longTemp,0,insert);
      System.arraycopy(columns[col],insert,longTemp,insert + 1,rowCount - insert);
      columns[col]=longTemp;
      break;
    }
case FLOAT:
{
    float[] floatTemp=new float[rowCount + 1];
    System.arraycopy(columns[col],0,floatTemp,0,insert);
    System.arraycopy(columns[col],insert,floatTemp,insert + 1,rowCount - insert);
    columns[col]=floatTemp;
    break;
  }
case DOUBLE:
{
  double[] doubleTemp=new double[rowCount + 1];
  System.arraycopy(columns[col],0,doubleTemp,0,insert);
  System.arraycopy(columns[col],insert,doubleTemp,insert + 1,rowCount - insert);
  columns[col]=doubleTemp;
  break;
}
case STRING:
{
String[] stringTemp=new String[rowCount + 1];
System.arraycopy(columns[col],0,stringTemp,0,insert);
System.arraycopy(columns[col],insert,stringTemp,insert + 1,rowCount - insert);
columns[col]=stringTemp;
break;
}
}
}
++rowCount;
setRow(insert,columnData);
}
