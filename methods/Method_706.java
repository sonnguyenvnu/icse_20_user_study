public void writeFieldValue(char seperator,String name,Enum<?> value){
  if (value == null) {
    write(seperator);
    writeFieldName(name);
    writeNull();
    return;
  }
  if (writeEnumUsingName && !writeEnumUsingToString) {
    writeEnumFieldValue(seperator,name,value.name());
  }
 else   if (writeEnumUsingToString) {
    writeEnumFieldValue(seperator,name,value.toString());
  }
 else {
    writeFieldValue(seperator,name,value.ordinal());
  }
}
