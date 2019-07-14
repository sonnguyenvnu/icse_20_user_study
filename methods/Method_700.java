public void writeFieldValue(char seperator,String name,char value){
  write(seperator);
  writeFieldName(name);
  if (value == 0) {
    writeString("\u0000");
  }
 else {
    writeString(Character.toString(value));
  }
}
