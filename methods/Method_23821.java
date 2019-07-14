protected void convertRow(DataOutputStream output,String[] pieces) throws IOException {
  if (pieces.length > getColumnCount()) {
    throw new IllegalArgumentException("Row with too many columns: " + PApplet.join(pieces,","));
  }
  for (int col=0; col < pieces.length; col++) {
switch (columnTypes[col]) {
case STRING:
      output.writeUTF(pieces[col]);
    break;
case INT:
  output.writeInt(PApplet.parseInt(pieces[col],missingInt));
break;
case LONG:
try {
output.writeLong(Long.parseLong(pieces[col]));
}
 catch (NumberFormatException nfe) {
output.writeLong(missingLong);
}
break;
case FLOAT:
output.writeFloat(PApplet.parseFloat(pieces[col],missingFloat));
break;
case DOUBLE:
try {
output.writeDouble(Double.parseDouble(pieces[col]));
}
 catch (NumberFormatException nfe) {
output.writeDouble(missingDouble);
}
break;
case CATEGORY:
String peace=pieces[col];
if (peace.equals(missingString)) {
output.writeInt(missingCategory);
}
 else {
output.writeInt(columnCategories[col].index(peace));
}
break;
}
}
for (int col=pieces.length; col < getColumnCount(); col++) {
switch (columnTypes[col]) {
case STRING:
output.writeUTF("");
break;
case INT:
output.writeInt(missingInt);
break;
case LONG:
output.writeLong(missingLong);
break;
case FLOAT:
output.writeFloat(missingFloat);
break;
case DOUBLE:
output.writeDouble(missingDouble);
break;
case CATEGORY:
output.writeInt(missingCategory);
break;
}
}
}
