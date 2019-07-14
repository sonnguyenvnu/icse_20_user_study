@ReactMethod public void hasSystemFeature(String feature,Promise p){
  if (feature == null || feature == "") {
    p.resolve(false);
    return;
  }
  boolean hasFeature=this.reactContext.getApplicationContext().getPackageManager().hasSystemFeature(feature);
  p.resolve(hasFeature);
}
