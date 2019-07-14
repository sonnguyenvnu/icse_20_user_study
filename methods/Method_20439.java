static String getDefaultValue(TypeName attributeType){
  if (attributeType == BOOLEAN) {
    return "false";
  }
 else   if (attributeType == INT) {
    return "0";
  }
 else   if (attributeType == BYTE) {
    return "(byte) 0";
  }
 else   if (attributeType == CHAR) {
    return "(char) 0";
  }
 else   if (attributeType == SHORT) {
    return "(short) 0";
  }
 else   if (attributeType == LONG) {
    return "0L";
  }
 else   if (attributeType == FLOAT) {
    return "0.0f";
  }
 else   if (attributeType == DOUBLE) {
    return "0.0d";
  }
 else {
    return "null";
  }
}
