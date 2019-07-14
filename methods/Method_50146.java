/** 
 * Field names with under_scores are converted to camelCase. 
 */
private String getFieldName(FieldDescriptor field){
  String fieldName=field.getName();
  return fieldName.contains("_") ? UNDERSCORE_TO_CAMEL.convert(fieldName) : fieldName;
}
