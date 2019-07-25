protected static GeoContextMapping load(String name,Map<String,Object> config){
  final GeoContextMapping.Builder builder=new GeoContextMapping.Builder(name);
  if (config != null) {
    final Object configPrecision=config.get(FIELD_PRECISION);
    if (configPrecision != null) {
      if (configPrecision instanceof Integer) {
        builder.precision((Integer)configPrecision);
      }
 else       if (configPrecision instanceof Long) {
        builder.precision((Long)configPrecision);
      }
 else       if (configPrecision instanceof Double) {
        builder.precision((Double)configPrecision);
      }
 else       if (configPrecision instanceof Float) {
        builder.precision((Float)configPrecision);
      }
 else {
        builder.precision(configPrecision.toString());
      }
      config.remove(FIELD_PRECISION);
    }
    final Object fieldName=config.get(FIELD_FIELDNAME);
    if (fieldName != null) {
      builder.field(fieldName.toString());
      config.remove(FIELD_FIELDNAME);
    }
  }
  return builder.build();
}
