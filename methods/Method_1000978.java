/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature disabled.
 */
public SerializationConfig without(FormatFeature feature){
  int newSet=_formatWriteFeatures & ~feature.getMask();
  return (_formatWriteFeatures == newSet) ? this : new SerializationConfig(this,_serFeatures,_streamWriteFeatures,newSet);
}
