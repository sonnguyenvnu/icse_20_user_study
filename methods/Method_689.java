public void config(SerializerFeature feature,boolean state){
  if (state) {
    features|=feature.getMask();
    if (feature == SerializerFeature.WriteEnumUsingToString) {
      features&=~SerializerFeature.WriteEnumUsingName.getMask();
    }
 else     if (feature == SerializerFeature.WriteEnumUsingName) {
      features&=~SerializerFeature.WriteEnumUsingToString.getMask();
    }
  }
 else {
    features&=~feature.getMask();
  }
  computeFeatures();
}
