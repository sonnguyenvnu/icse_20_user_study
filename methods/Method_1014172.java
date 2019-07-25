/** 
 * Transforms the given  {@code value} into its JSON representation using {@code Gson}. Since we do not know the type of  {@code value} whiledeserializing it afterwards we prepend its qualified type name to the JSON String.
 * @param value the {@code value} to store
 * @return the JSON document prepended with the qualified type name of {@code value}
 */
private String serialize(T value){
  if (value == null) {
    throw new IllegalArgumentException("Cannot serialize NULL");
  }
  String valueTypeName=value.getClass().getName();
  String valueAsString=mapper.toJson(value);
  String concatValue=valueTypeName + TYPE_SEPARATOR + valueAsString;
  logger.trace("serialized value '{}' to MapDB",concatValue);
  return concatValue;
}
