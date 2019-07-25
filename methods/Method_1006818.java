public String match(String sql,String sqlType){
  String dateV=map.get(DATE);
  String byteV=map.get(BYTE);
  String intV=map.get(INT);
  String longV=map.get(LONG);
  String bigV=map.get(BIG);
  String textV=map.get(TEXT);
  String longTextV=map.get(LONG_TEXT);
  String stringV=map.get(STRING);
  String increamentV=map.get(INCREAMENT);
  String engineV=map.get(ENGINE);
  return sql.replace(DATE.trim(),dateV).replace(BYTE.trim(),byteV).replace(INT.trim(),intV).replace(LONG.trim(),longV).replace(BIG.trim(),bigV).replace(TEXT.trim(),textV).replace(LONG_TEXT.trim(),longTextV).replace(STRING.trim(),stringV).replace(INCREAMENT.trim(),increamentV).replace(ENGINE.trim(),engineV);
}
