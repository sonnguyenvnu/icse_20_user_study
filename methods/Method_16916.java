private void addVariableExpiration(){
  if (context.generateFeatures.contains(Feature.EXPIRE_ACCESS)) {
    addLink("previousInVariableOrder","previousInAccessOrder");
    addLink("nextInVariableOrder","nextInAccessOrder");
    addVariableTime("accessTime");
  }
 else   if (context.generateFeatures.contains(Feature.EXPIRE_WRITE)) {
    addLink("previousInVariableOrder","previousInWriteOrder");
    addLink("nextInVariableOrder","nextInWriteOrder");
    addVariableTime("writeTime");
  }
  if (context.parentFeatures.contains(Feature.EXPIRE_ACCESS) && context.parentFeatures.contains(Feature.EXPIRE_WRITE) && context.generateFeatures.contains(Feature.REFRESH_WRITE)) {
    addLink("previousInVariableOrder","previousInWriteOrder");
    addLink("nextInVariableOrder","nextInWriteOrder");
    addVariableTime("accessTime");
  }
}
