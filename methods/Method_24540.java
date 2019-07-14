protected void blendModeImpl(){
  if (blendMode != REPLACE && blendMode != BLEND) {
    showMissingWarning("blendMode");
  }
}
