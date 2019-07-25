/** 
 * Method for constructing a new reader instance that is configured with specified feature disabled.
 */
public ObjectReader without(StreamReadFeature feature){
  return _with(_config.without(feature));
}
