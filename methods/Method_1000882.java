/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature disabled.
 */
public DeserializationConfig without(DeserializationFeature feature){
  int newDeserFeatures=_deserFeatures & ~feature.getMask();
  return (newDeserFeatures == _deserFeatures) ? this : new DeserializationConfig(this,newDeserFeatures,_streamReadFeatures,_formatReadFeatures);
}
