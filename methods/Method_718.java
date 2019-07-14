/** 
 * Sets serializer feature.
 * @param features the features
 * @see FastJsonConfig#setSerializerFeatures(SerializerFeature)
 * @deprecated
 */
@Deprecated public void setSerializerFeature(SerializerFeature... features){
  this.fastJsonConfig.setSerializerFeatures(features);
}
