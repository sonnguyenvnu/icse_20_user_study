/** 
 * Sets the column type. If data already exists, then it'll be converted to the new type.
 * @param column the column whose type should be changed
 * @param newType something fresh, maybe try an int or a float for size?
 */
public void setColumnType(int column,int newType){
switch (newType) {
case INT:
{
      int[] intData=new int[rowCount];
      for (int row=0; row < rowCount; row++) {
        String s=getString(row,column);
        intData[row]=(s == null) ? missingInt : PApplet.parseInt(s,missingInt);
      }
      columns[column]=intData;
      break;
    }
case LONG:
{
    long[] longData=new long[rowCount];
    for (int row=0; row < rowCount; row++) {
      String s=getString(row,column);
      try {
        longData[row]=(s == null) ? missingLong : Long.parseLong(s);
      }
 catch (      NumberFormatException nfe) {
        longData[row]=missingLong;
      }
    }
    columns[column]=longData;
    break;
  }
case FLOAT:
{
  float[] floatData=new float[rowCount];
  for (int row=0; row < rowCount; row++) {
    String s=getString(row,column);
    floatData[row]=(s == null) ? missingFloat : PApplet.parseFloat(s,missingFloat);
  }
  columns[column]=floatData;
  break;
}
case DOUBLE:
{
double[] doubleData=new double[rowCount];
for (int row=0; row < rowCount; row++) {
  String s=getString(row,column);
  try {
    doubleData[row]=(s == null) ? missingDouble : Double.parseDouble(s);
  }
 catch (  NumberFormatException nfe) {
    doubleData[row]=missingDouble;
  }
}
columns[column]=doubleData;
break;
}
case STRING:
{
if (columnTypes[column] != STRING) {
String[] stringData=new String[rowCount];
for (int row=0; row < rowCount; row++) {
  stringData[row]=getString(row,column);
}
columns[column]=stringData;
}
break;
}
case CATEGORY:
{
int[] indexData=new int[rowCount];
HashMapBlows categories=new HashMapBlows();
for (int row=0; row < rowCount; row++) {
String s=getString(row,column);
indexData[row]=categories.index(s);
}
columnCategories[column]=categories;
columns[column]=indexData;
break;
}
default :
{
throw new IllegalArgumentException("That's not a valid column type.");
}
}
columnTypes[column]=newType;
}
