/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified features enabled.
 */
public DeserializationConfig with(StreamReadFeature feature){
  int newSet=_streamReadFeatures | feature.getMask();
  return (_streamReadFeatures == newSet) ? this : new DeserializationConfig(this,_deserFeatures,newSet,_formatReadFeatures);
}
