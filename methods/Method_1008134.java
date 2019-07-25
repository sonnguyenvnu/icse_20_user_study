private Object value(FieldMapper fieldMapper,Object rowValue,boolean valueForSearch){
  if (fieldMapper != null) {
    final MappedFieldType fieldType=fieldMapper.fieldType();
    return (valueForSearch) ? fieldType.valueForDisplay(rowValue) : fieldType.cqlValue(rowValue);
  }
 else {
    return rowValue;
  }
}
