/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature disabled.
 */
public DeserializationConfig without(StreamReadFeature feature){
  int newSet=_streamReadFeatures & ~feature.getMask();
  return (_streamReadFeatures == newSet) ? this : new DeserializationConfig(this,_deserFeatures,newSet,_formatReadFeatures);
}
