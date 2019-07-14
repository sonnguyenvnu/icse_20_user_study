public void writeFieldValue(char seperator,String name,BigDecimal value){
  write(seperator);
  writeFieldName(name);
  if (value == null) {
    writeNull();
  }
 else {
    int scale=value.scale();
    write(isEnabled(SerializerFeature.WriteBigDecimalAsPlain) && scale >= -100 && scale < 100 ? value.toPlainString() : value.toString());
  }
}
