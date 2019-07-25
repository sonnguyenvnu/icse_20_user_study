/** 
 * Method for constructing a new reader instance that is configured with specified feature disabled.
 */
public ObjectReader without(FormatFeature feature){
  return _with(_config.without(feature));
}
