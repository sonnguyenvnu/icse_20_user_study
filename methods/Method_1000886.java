/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified features enabled.
 */
public DeserializationConfig with(FormatFeature feature){
  int newSet=_formatReadFeatures | feature.getMask();
  return (_formatReadFeatures == newSet) ? this : new DeserializationConfig(this,_deserFeatures,_streamReadFeatures,newSet);
}
