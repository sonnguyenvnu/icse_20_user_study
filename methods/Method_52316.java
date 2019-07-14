@Override public void start(RuleContext ctx){
  closeTargets.clear();
  simpleTypes.clear();
  types.clear();
  if (getProperty(CLOSE_TARGETS_DESCRIPTOR) != null) {
    closeTargets.addAll(getProperty(CLOSE_TARGETS_DESCRIPTOR));
  }
  if (getProperty(USE_CLOSE_AS_DEFAULT_TARGET) && !closeTargets.contains("close")) {
    closeTargets.add("close");
  }
  if (getProperty(TYPES_DESCRIPTOR) != null) {
    types.addAll(getProperty(TYPES_DESCRIPTOR));
    for (    String type : getProperty(TYPES_DESCRIPTOR)) {
      simpleTypes.add(toSimpleType(type));
    }
  }
}
