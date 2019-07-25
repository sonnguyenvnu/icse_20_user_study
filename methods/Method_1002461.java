FieldOverridesProvider build(){
  return new FieldOverridesProvider(){
    @Override public FieldOverride getDefaultValueOverride(    RecordDataSchema.Field field){
      return (_defaultValueOverrides == null) ? null : _defaultValueOverrides.get(field);
    }
    @Override public FieldOverride getSchemaOverride(    RecordDataSchema.Field field){
      return (_schemaOverrides == null) ? null : _schemaOverrides.get(field);
    }
  }
;
}
