public void writeFieldValue(char seperator,String name,double value){
  write(seperator);
  writeFieldName(name);
  writeDouble(value,false);
}
