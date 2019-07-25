/** 
 * Create a new parser that will use the specified resolver and validation options.
 * @param resolver to be provided to the parser.
 * @return a new parser.
 */
public SchemaParser create(DataSchemaResolver resolver){
  SchemaParser parser=new SchemaParser(resolver);
  if (_validationOptions != null) {
    parser.setValidationOptions(_validationOptions);
  }
  return parser;
}
