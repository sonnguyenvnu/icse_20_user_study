/** 
 * Fluent factory method that will construct and return a new configuration object instance with specified features enabled.
 */
public DeserializationConfig with(DeserializationFeature first,DeserializationFeature... features){
  int newDeserFeatures=_deserFeatures | first.getMask();
  for (  DeserializationFeature f : features) {
    newDeserFeatures|=f.getMask();
  }
  return (newDeserFeatures == _deserFeatures) ? this : new DeserializationConfig(this,newDeserFeatures,_streamReadFeatures,_formatReadFeatures);
}
