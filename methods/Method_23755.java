static int parseColumnType(String columnType){
  columnType=columnType.toLowerCase();
  int type=-1;
  if (columnType.equals("string")) {
    type=STRING;
  }
 else   if (columnType.equals("int")) {
    type=INT;
  }
 else   if (columnType.equals("long")) {
    type=LONG;
  }
 else   if (columnType.equals("float")) {
    type=FLOAT;
  }
 else   if (columnType.equals("double")) {
    type=DOUBLE;
  }
 else   if (columnType.equals("category")) {
    type=CATEGORY;
  }
 else {
    throw new IllegalArgumentException("'" + columnType + "' is not a valid column type.");
  }
  return type;
}
