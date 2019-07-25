/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified feature disabled.
 */
public SerializationConfig without(SerializationFeature feature){
  int newSerFeatures=_serFeatures & ~feature.getMask();
  return (newSerFeatures == _serFeatures) ? this : new SerializationConfig(this,newSerFeatures,_streamWriteFeatures,_formatWriteFeatures);
}
