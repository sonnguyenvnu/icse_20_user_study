/** 
 * Resolve a  {@link ValuesSourceConfig} given configuration parameters.
 */
public static <VS extends ValuesSource>ValuesSourceConfig<VS> resolve(QueryShardContext context,ValueType valueType,String field,Script script,Object missing,DateTimeZone timeZone,String format){
  if (field == null) {
    if (script == null) {
      @SuppressWarnings("unchecked") ValuesSourceConfig<VS> config=new ValuesSourceConfig<>(ValuesSourceType.ANY);
      config.format(resolveFormat(null,valueType));
      return config;
    }
    ValuesSourceType valuesSourceType=valueType != null ? valueType.getValuesSourceType() : ValuesSourceType.ANY;
    if (valuesSourceType == ValuesSourceType.ANY) {
      valuesSourceType=ValuesSourceType.BYTES;
    }
    ValuesSourceConfig<VS> config=new ValuesSourceConfig<VS>(valuesSourceType);
    config.missing(missing);
    config.timezone(timeZone);
    config.format(resolveFormat(format,valueType));
    config.script(createScript(script,context));
    config.scriptValueType(valueType);
    return config;
  }
  MappedFieldType fieldType=context.fieldMapper(field);
  if (fieldType == null) {
    ValuesSourceType valuesSourceType=valueType != null ? valueType.getValuesSourceType() : ValuesSourceType.ANY;
    ValuesSourceConfig<VS> config=new ValuesSourceConfig<>(valuesSourceType);
    config.missing(missing);
    config.timezone(timeZone);
    config.format(resolveFormat(format,valueType));
    config.unmapped(true);
    if (valueType != null) {
      config.scriptValueType(valueType);
    }
    return config;
  }
  IndexFieldData<?> indexFieldData=context.getForField(fieldType);
  ValuesSourceConfig<VS> config;
  if (valueType == null) {
    if (indexFieldData instanceof IndexNumericFieldData) {
      config=new ValuesSourceConfig<>(ValuesSourceType.NUMERIC);
    }
 else     if (indexFieldData instanceof IndexGeoPointFieldData) {
      config=new ValuesSourceConfig<>(ValuesSourceType.GEOPOINT);
    }
 else {
      config=new ValuesSourceConfig<>(ValuesSourceType.BYTES);
    }
  }
 else {
    config=new ValuesSourceConfig<>(valueType.getValuesSourceType());
  }
  config.fieldContext(new FieldContext(field,indexFieldData,fieldType));
  config.missing(missing);
  config.timezone(timeZone);
  config.script(createScript(script,context));
  config.format(fieldType.docValueFormat(format,timeZone));
  return config;
}
