/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature disabled.
 */
public DeserializationConfig without(FormatFeature feature){
  int newSet=_formatReadFeatures & ~feature.getMask();
  return (_formatReadFeatures == newSet) ? this : new DeserializationConfig(this,_deserFeatures,_streamReadFeatures,newSet);
}
