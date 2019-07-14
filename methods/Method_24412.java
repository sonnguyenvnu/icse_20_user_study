@Override public void enableStyle(){
  if (savedStroke) {
    setStroke(true);
    setStroke(savedStrokeColor);
    setStrokeWeight(savedStrokeWeight);
    setStrokeCap(savedStrokeCap);
    setStrokeJoin(savedStrokeJoin);
  }
 else {
    setStroke(false);
  }
  if (savedFill) {
    setFill(true);
    setFill(savedFillColor);
  }
 else {
    setFill(false);
  }
  if (savedTint) {
    setTint(true);
    setTint(savedTintColor);
  }
  setAmbient(savedAmbientColor);
  setSpecular(savedSpecularColor);
  setEmissive(savedEmissiveColor);
  setShininess(savedShininess);
  if (image != null) {
    setTextureMode(savedTextureMode);
  }
  super.enableStyle();
}
