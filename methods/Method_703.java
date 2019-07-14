public void writeFieldValue(char seperator,String name,float value){
  write(seperator);
  writeFieldName(name);
  writeFloat(value,false);
}
