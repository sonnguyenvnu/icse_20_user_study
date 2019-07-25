/** 
 * Method for constructing a new instance that is configured with specified feature disabled.
 */
public ObjectWriter without(SerializationFeature feature){
  return _new(this,_config.without(feature));
}
