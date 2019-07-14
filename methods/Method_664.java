protected final void writeKeyValue(char seperator,String key,Object value){
  if (seperator != '\0') {
    out.write(seperator);
  }
  out.writeFieldName(key);
  write(value);
}
