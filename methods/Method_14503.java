static public void registerOverlayModel(String modelName,Class<? extends OverlayModel> klass){
  s_overlayModelClasses.put(modelName,klass);
}
