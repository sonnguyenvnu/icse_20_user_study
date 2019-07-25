/** 
 * Method for constructing a new instance that is configured with specified features disabled.
 */
public ObjectWriter without(SerializationFeature first,SerializationFeature... other){
  return _new(this,_config.without(first,other));
}
