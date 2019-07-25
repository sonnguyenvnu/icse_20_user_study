/** 
 * Deserialize the JSON of the template
 * @param json                       the template json
 * @param includeEncryptedProperties if true the encrypted properties will be returned.  false will set the property values to ""
 * @return the registered template
 */
private RegisteredTemplate deserialize(String json,boolean includeEncryptedProperties){
  RegisteredTemplate template=ObjectMapperSerializer.deserialize(json,RegisteredTemplate.class);
  template.getProperties().stream().filter(nifiProperty -> nifiProperty.isSensitive()).forEach(nifiProperty -> {
    if (!includeEncryptedProperties) {
      nifiProperty.setValue("");
    }
  }
);
  return template;
}
