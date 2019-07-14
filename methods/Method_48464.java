public boolean hasPropertyPrefetching(){
  if (propertyPrefetching == null) {
    return getStoreFeatures().isDistributed();
  }
 else {
    return propertyPrefetching;
  }
}
