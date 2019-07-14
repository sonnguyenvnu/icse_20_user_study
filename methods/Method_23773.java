protected void setRowCol(int row,int col,Object piece){
switch (columnTypes[col]) {
case STRING:
    String[] stringData=(String[])columns[col];
  if (piece == null) {
    stringData[row]=null;
  }
 else {
    stringData[row]=String.valueOf(piece);
  }
break;
case INT:
int[] intData=(int[])columns[col];
if (piece == null) {
intData[row]=missingInt;
}
 else if (piece instanceof Integer) {
intData[row]=(Integer)piece;
}
 else {
intData[row]=PApplet.parseInt(String.valueOf(piece),missingInt);
}
break;
case LONG:
long[] longData=(long[])columns[col];
if (piece == null) {
longData[row]=missingLong;
}
 else if (piece instanceof Long) {
longData[row]=(Long)piece;
}
 else {
try {
longData[row]=Long.parseLong(String.valueOf(piece));
}
 catch (NumberFormatException nfe) {
longData[row]=missingLong;
}
}
break;
case FLOAT:
float[] floatData=(float[])columns[col];
if (piece == null) {
floatData[row]=missingFloat;
}
 else if (piece instanceof Float) {
floatData[row]=(Float)piece;
}
 else {
floatData[row]=PApplet.parseFloat(String.valueOf(piece),missingFloat);
}
break;
case DOUBLE:
double[] doubleData=(double[])columns[col];
if (piece == null) {
doubleData[row]=missingDouble;
}
 else if (piece instanceof Double) {
doubleData[row]=(Double)piece;
}
 else {
try {
doubleData[row]=Double.parseDouble(String.valueOf(piece));
}
 catch (NumberFormatException nfe) {
doubleData[row]=missingDouble;
}
}
break;
case CATEGORY:
int[] indexData=(int[])columns[col];
if (piece == null) {
indexData[row]=missingCategory;
}
 else {
String peace=String.valueOf(piece);
if (peace.equals(missingString)) {
indexData[row]=missingCategory;
}
 else {
indexData[row]=columnCategories[col].index(peace);
}
}
break;
default :
throw new IllegalArgumentException("That's not a valid column type.");
}
}
