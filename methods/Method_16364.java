protected ValueConverter initColumnValueConvert(JDBCType jdbcType,Class javaType){
  boolean isBasicClass=!classMapping.values().contains(javaType) || javaType != Map.class || javaType != List.class;
  if (javaType.isEnum() && EnumDict.class.isAssignableFrom(javaType)) {
    return new EnumDictValueConverter<EnumDict>(() -> (List)Arrays.asList(javaType.getEnumConstants()));
  }
switch (jdbcType) {
case BLOB:
    if (!isBasicClass) {
      return new JSONValueConverter(javaType,new BlobValueConverter());
    }
  return new BlobValueConverter();
case CLOB:
if (!isBasicClass) {
  return new JSONValueConverter(javaType,new ClobValueConverter());
}
return new ClobValueConverter();
case NUMERIC:
case BIGINT:
case INTEGER:
case SMALLINT:
case TINYINT:
return new NumberValueConverter(javaType);
case DATE:
case TIMESTAMP:
case TIME:
return new DateTimeConverter("yyyy-MM-dd HH:mm:ss",javaType);
default :
if (!isBasicClass) {
return new JSONValueConverter(javaType,new DefaultValueConverter());
}
if (javaType == String.class && (jdbcType == JDBCType.VARCHAR || jdbcType == JDBCType.NVARCHAR)) {
return new DefaultValueConverter(){
@Override public Object getData(Object value){
if (value instanceof Number) {
return value.toString();
}
return super.getData(value);
}
}
;
}
return new DefaultValueConverter();
}
}
