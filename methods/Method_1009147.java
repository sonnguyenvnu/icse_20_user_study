/** 
 * Build a frozen instance of this factory configuration
 * @return a new {@link JsonSchemaFactory}
 * @see JsonSchemaFactory#JsonSchemaFactory(JsonSchemaFactoryBuilder)
 */
@Override public JsonSchemaFactory freeze(){
  return new JsonSchemaFactory(this);
}
