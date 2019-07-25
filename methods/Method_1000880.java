/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified features enabled.
 */
public DeserializationConfig with(DeserializationFeature feature){
  int newDeserFeatures=(_deserFeatures | feature.getMask());
  return (newDeserFeatures == _deserFeatures) ? this : new DeserializationConfig(this,newDeserFeatures,_streamReadFeatures,_formatReadFeatures);
}
