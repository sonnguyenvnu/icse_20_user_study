/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature enabled.
 */
public SerializationConfig with(StreamWriteFeature feature){
  int newSet=_streamWriteFeatures | feature.getMask();
  return (_streamWriteFeatures == newSet) ? this : new SerializationConfig(this,_serFeatures,newSet,_formatWriteFeatures);
}
