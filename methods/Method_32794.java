@ReactMethod public void getSystemAvailableFeatures(Promise p){
  final FeatureInfo[] featureList=this.reactContext.getApplicationContext().getPackageManager().getSystemAvailableFeatures();
  WritableArray promiseArray=Arguments.createArray();
  for (  FeatureInfo f : featureList) {
    if (f.name != null) {
      promiseArray.pushString(f.name);
    }
  }
  p.resolve(promiseArray);
}
