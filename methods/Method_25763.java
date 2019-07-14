private String literalFix(boolean value,boolean autoboxFix){
  if (autoboxFix) {
    return value ? "true" : "false";
  }
  return value ? "Boolean.TRUE" : "Boolean.FALSE";
}
